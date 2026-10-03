package edu.uees.testing.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReservaServiceTest {

    private ReservaService servicio;

    @BeforeEach
    void setUp() {
        servicio = new ReservaService(null, null, null);
    }

    @Test
    @DisplayName("CP-01: Cancelación con anticipación normal (5h) permitida")
    void cincoHorasPermitenCancelar() {
        // Arrange & Act & Assert
        assertTrue(servicio.puedeCancelar(5));
    }

    @Test
    @DisplayName("CP-02: Cancelación en el límite exacto (2h) permitida")
    void dosHorasPermitenCancelar() {
        // Arrange & Act & Assert
        assertTrue(servicio.puedeCancelar(2));
    }

    @Test
    @DisplayName("CP-03: Cancelación en frontera inferior (1h) denegada")
    void unaHoraNoPermiteCancelar() {
        // Arrange & Act & Assert
        assertFalse(servicio.puedeCancelar(1));
    }

    @Test
    @DisplayName("CP-04: Cancelación sin anticipación (0h) denegada")
    void ceroHorasNoPermitenCancelar() {
        // Arrange & Act & Assert
        assertFalse(servicio.puedeCancelar(0));
    }

    @Test
    @DisplayName("CP-05: Cancelación con horas negativas denegada")
    void horasNegativasNoPermitenCancelar() {
        // Arrange & Act & Assert
        assertFalse(servicio.puedeCancelar(-1));
    }

    @Test
    @DisplayName("CP-06: Cliente NORMAL no recibe descuento")
    void normalNoRecibeDescuento() {
        // Arrange & Act & Assert
        assertEquals(100.0, servicio.calcularTotal("NORMAL", 100.0), 0.001);
    }

    @Test
    @DisplayName("CP-07: Cliente VIP recibe 15% de descuento")
    void vipRecibeQuincePorCiento() {
        // Arrange
        double base = 100.0;
        // Act
        double resultado = servicio.calcularTotal("VIP", base);
        // Assert
        assertEquals(85.0, resultado, 0.001);
    }

    @Test
    @DisplayName("CP-08: Cliente ESTUDIANTE recibe 10% de descuento")
    void estudianteRecibeDiezPorCiento() {
        // Arrange
        double base = 100.0;
        // Act
        double resultado = servicio.calcularTotal("ESTUDIANTE", base);
        // Assert
        assertEquals(90.0, resultado, 0.001);
    }

    @Test
    @DisplayName("CP-09: Total base cero devuelve cero en cliente VIP")
    void totalBaseCeroDevuelveCero() {
        // Arrange & Act & Assert
        assertEquals(0.0, servicio.calcularTotal("VIP", 0.0), 0.001);
    }

    @Test
    @DisplayName("CP-10: Total base negativo lanza IllegalArgumentException")
    void totalNegativoLanzaExcepcion() {
        // Arrange & Act & Assert
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> servicio.calcularTotal("NORMAL", -1.0)
        );
        assertEquals("Total base inválido", ex.getMessage());
    }
}
