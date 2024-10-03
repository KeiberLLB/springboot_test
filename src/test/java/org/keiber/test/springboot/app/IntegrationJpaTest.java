package org.keiber.test.springboot.app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.keiber.test.springboot.app.models.Cuenta;
import org.keiber.test.springboot.app.repositories.CuentaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Optional;

@DataJpaTest
public class IntegrationJpaTest {
    @Autowired
    CuentaRepository cuentaRepository;

    @BeforeEach
    void setUp() {
        cuentaRepository.save(new Cuenta(1L, "Andrés", new BigDecimal("1000")));
        cuentaRepository.save(new Cuenta(2L, "Jhon", new BigDecimal("2000")));
    }

    @Test
    void testFindById() {
        Optional<Cuenta> cuenta = cuentaRepository.findByPersona("Andrés");
        assertTrue(cuenta.isPresent());
        assertEquals("Andrés", cuenta.orElseThrow().getPersona());
    }

    @Test
    void testFindByPersona() {
        Optional<Cuenta> cuenta = cuentaRepository.findById(1L);
        assertTrue(cuenta.isPresent());
        assertEquals("Andrés", cuenta.orElseThrow().getPersona());
        assertEquals("1000", cuenta.orElseThrow().getSaldo().toPlainString());
    }
}




