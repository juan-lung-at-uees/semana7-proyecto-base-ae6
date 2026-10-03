# UEES UCOM0310 — Semana 7 — Proyecto base Ae6
Proyecto base para las actividades individuales de Semana 7.

## Objetivo del Proyecto
Blindar las reglas de negocio del servicio de reservas (ReservaService), abarcando políticas de cancelacion anticipada, cálculo de descuentos segun membresía y confirmación con aislamiento de dependencias externas.

## Requisitos
- Java 21
- Maven 3.9+
- Git

## Verificación inicial y Pruebas
Comando para compilar y ejecutar la suite completa desde un estado limpio:
```bash
mvn clean test
```

## Cómo Generar y Abrir JaCoCo
La cobertura se genera automáticamente durante la fase de prueba (test) mediante `jacoco-maven-plugin`.

Para inspeccionar el reporte HTML generado abra en un navegador el archivo `target/site/jacoco/index.html`

## Regla de trabajo
No modifiques el código productivo solo para hacer pasar una prueba sin justificar el cambio.
Primero diseña el caso, luego implementa la prueba y finalmente interpreta el resultado.

## Resumen de los Casos Incluidos
Se diseñó e implementó una matriz de 14 casos de prueba estructurados bajo el patrón AAA (Arrange, Act, Assert):
- Cancelación: Escenario normal (5h), límites de frontera (2h y 1h), extremo sin anticipación (0h) y tiempo negativo (-1h).
- Descuentos: `NORMAL` (sin descuento), `VIP` (15%), `ESTUDIANTE` (10%), límite en total base cero (`0.0`) y excepción ante bases negativas.
- Confirmación: Flujo exitoso disponible con persistencia y notificación, rechazo por indisponibilidad sin efectos secundarios, y validacion de reserva nula.
- Cierre de huecos JaCoCo: Asignación de tipo por defecto ante nulo y método `cancelar()` en la entidad Reserva.

## Rama Utilizada
- Nombre de la rama: `ae6/suite-pruebas` (creada a partir de `main`)

## Enlace al Pull Request
- Pull Request (PR) #1 [`test: completar suite, cobertura y evidencia de Ae6`](https://github.com/juan-lung-at-uees/semana7-proyecto-base-ae6/pull/1) - desde `ae6/suite-pruebas` hacia `main` 

## Declaración de Uso de Inteligencia Artificial
_Declaro explícitamente el uso de inteligencia artificial (Google Gemini) como herramienta de mentoría técnica y arquitectónica para la refactorización del proyecto Maven, y generación automatizada del documento. Todas las decisiones de dominio y diseño representadas en este documento han sido analizadas, validadas y son de total comprensión de mi autoría como estudiante del curso._
