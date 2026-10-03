# Pull Request `test: completar suite, cobertura y evidencia de Ae6`

## Objetivo
Blindar las reglas críticas de negocio de `ReservaService` (cancelación, políticas de descuento y confirmación colaborativa) mediante una suite completa de pruebas unitarias con JUnit 5, Mockito y análisis técnico de cobertura JaCoCo.

## Cambios realizados
- Matriz de pruebas estructurada con 13 casos (normales, alternativos, límites exactos, extremos e inválidos).
- Implementación rigurosa de JUnit 5 siguiendo el patrón Arrange - Act - Assert (AAA) con aserciones atómicas y expresivas.
- Aislamiento con Mockito: `DisponibilidadClient` como Stub determinista; `ReservaRepository` y `Notificador` como Mocks con verificación de interacciones y ausencia de efectos colaterales.
- Análisis técnico crítico de métricas de cobertura y ramas con JaCoCo.

## Casos de prueba
- **Cancelación:** Normal (5h), Límite exacto (2h), Límite inferior (1h), Sin anticipación (0h), Horas negativas (-1h).
- **Descuentos:** NORMAL (sin descuento), VIP (15%), ESTUDIANTE (10%), Base cero (0.0), Total base negativo (IllegalArgumentException).
- **Confirmación:** Flujo disponible exitoso con persistencia y notificación, Flujo no disponible con IllegalStateException y sin efectos secundarios, Reserva nula con IllegalArgumentException y verificación de cero interacción.

## Cómo verificar
```bash
mvn clean test
```

## Cobertura
100% de instrucciones y 100% de ramas en `ReservaService`. Análisis documentado en [`docs/02_ANALISIS_COBERTURA_PLANTILLA.md`](02_ANALISIS_COBERTURA_PLANTILLA.md).
 
 ## Limitaciones
Las pruebas implementadas son exclusivamente unitarias y en memoria; no abarcan pruebas de integración de base de datos ni latencias de red con APIs reales.

## Autorrevisión
- [X] Compila
- [X] Pruebas en verde
- [X] Sin archivos accidentales
- [X] Commits descriptivos
- [X] Documentación actualizada

## Uso de IA
_Declaro explícitamente el uso de inteligencia artificial (Google Gemini) como herramienta de mentoría técnica y arquitectónica para la refactorización del proyecto Maven, y generación automatizada del documento. Todas las decisiones de dominio y diseño representadas en este documento han sido analizadas, validadas y son de total comprensión de mi autoría como estudiante del curso._
