package edu.uees.testing.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
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
}
