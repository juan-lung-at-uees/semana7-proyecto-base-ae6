# Matriz de casos de prueba - Ae6 (ReservaService)

| ID | Regla | Escenario | Entrada | Esperado | Tipo | Riesgo que protege |
|---|---|---|---|---|---|---|
| CP-01 | Cancelación | Anticipación normal amplia | `horas = 5` | `true` | Normal | Regla general de cancelación anticipada sin restricciones. |
| CP-02 | Cancelación | Límite exacto permitido | `horas = 2` | `true` | Límite | Evita el error típico de codificación > 2 en vez de >= 2. |
| CP-03 | Cancelación | Límite inferior no permitido | `horas = 1` | `false` | Límite | Protege la frontera inferior inmediata contra cancelaciones tardías. |
| CP-04 | Cancelación | Sin anticipación (extremo) | `horas = 0` | `false` | Límite / Extremo | Evita cancelaciones simultáneas o en el momento del evento. |
| CP-05 | Cancelación | Horas negativas inválidas | `horas = -1` | `false` | Inválido | Entrada fuera de dominio temporal o valores de datos corruptos. |
| CP-06 | Descuentos | Tarifa NORMAL estándar | `"NORMAL", 100.0` | `100.0` | Normal | Garantiza que no se altere el cobro en tarifas regulares. |
| CP-07 | Descuentos | Cliente VIP (15% desc.) | `"VIP", 100.0` | `85.0` | Alternativo | Evita cobros indebidos a miembros con descuento preferencial. |
| CP-08 | Descuentos | Cliente ESTUDIANTE (10% desc.) | `"ESTUDIANTE", 100.0` | `90.0` | Alternativo | Protege el convenio institucional de beneficio académico. |
| CP-09 | Descuentos | Total base cero (límite válido) | `"VIP", 0.0` | `0.0` | Límite | Evalúa la frontera exacta antes de entrar en montos negativos. |
| CP-10 | Descuentos | Total base negativo | `"NORMAL", -1.0` | Lanza `IllegalArgumentException` | Excepción | Facturación inconsistente o balances financieros negativos. |
| CP-11 | Confirmación | Horario disponible (flujo exitoso) | `reserva, disponible=true` | `CONFIRMADA`, `guardar()`, `enviarConfirmacion()` | Normal | Falla en persistir o notificar al usuario tras reserva confirmada. |
| CP-12 | Confirmación | Horario no disponible | `reserva, disponible=false` | Lanza `IllegalStateException`, sin guardar ni notificar | Alternativo / Excepción | Sobrevender cupos o notificar reservas no concretadas. |
| CP-13 | Confirmación | Reserva nula | `null` | Lanza `IllegalArgumentException`, cero interacción con dependencias | Inválido / Excepción | Fallos inesperados por puntero nulo y llamadas espurias a infraestructura. |
