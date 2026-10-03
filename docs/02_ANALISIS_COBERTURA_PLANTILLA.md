# Análisis de cobertura

## Resultado observado
- Cobertura de líneas: 100% en `ReservaService` (21/21 líneas ejecutadas, 0 missed lines); 87% global del proyecto (34/39 líneas, 5 missed en `Reserva`).
- Cobertura de ramas: 100% en `ReservaService` (10/10 ramas evaluadas, 0 missed branches); 83% global (15/18 ramas, 3 missed en constructor de `Reserva`).
- Clase o método analizado: `edu.uees.testing.service.ReservaService` (analizada en conjunto con la entidad de dominio `edu.uees.testing.domain.Reserva`).

## Huecos relevantes
1. **Métodos no invocados y ramas no cubiertas en `Reserva`:** El reporte detallado muestra que `cancelar()` (0% cobertura, 2 líneas sin ejecutar), `getId()` (0%) y `getTipo()` (0%) poseen 0% de cobertura. Asimismo, el constructor `Reserva(String, String)` presenta 50% de cobertura de ramas (3 de 6 ramas perdidas por validaciones de `id == null || id.isBlank()` y el operador ternario `tipo == null ? "NORMAL" : tipo`).
2. **Hueco semántico en `ReservaService` vs `Reserva`:** Aunque `ReservaService.confirmar()` alcanza 100% de líneas y ramas, la suite interactuaba únicamente con `confirmar()` y `getEstado()`, dejando desprotegido el método `cancelar()` de la entidad cuando el servicio evalúa la regla `puedeCancelar()`.

## Decisiones
- ¿Qué prueba nueva se añadió? Se añadió la prueba de transición y estado `reservaPuedeCancelarseYCambiaEstado()` que instancia `new Reserva("R-01", null)`, valida la asignación por defecto del tipo "NORMAL" (cubriendo la rama ternaria) y ejecuta `reserva.cancelar()`, verificando `assertEquals(EstadoReserva.CANCELADA, reserva.getEstado())` y `assertEquals("NORMAL", reserva.getTipo())`.
- ¿Qué riesgo protege? Protege la consistencia del ciclo de vida de la entidad de dominio, garantizando que el estado `CANCELADA` no sea corrompido y que la inicialización con tipo nulo no lance excepciones no controladas.
- ¿Por qué no basta con el porcentaje? Porque `ReservaService` exhibe 100% de líneas y ramas (21/21 líneas, 10/10 ramas), lo que podría dar una falsa sensación de seguridad; sin embargo, no detectaría un fallo si un método mutador como `puedeCancelar` tuviera un error conceptual de límites (`>= 1`), ni garantiza que las entidades colaboradoras mantengan sus invariantes si no se auditan sus interacciones y estados con aserciones rigurosas.
