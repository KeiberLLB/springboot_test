package org.keiber.test.springboot.app.controllers;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.keiber.test.springboot.app.Datos;
import org.keiber.test.springboot.app.models.TransaccionDTO;
import org.keiber.test.springboot.app.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@WebMvcTest(controllers = CuentaController.class)
public class CuentaControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private CuentaService cuentaService;

  ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    objectMapper = new ObjectMapper();
  }

  @Test
  void testDetalle() throws Exception {
    // Given
    when(cuentaService.findById(1L)).thenReturn(Datos.crearCuenta001().orElseThrow());

    // When
    mvc.perform(get("/api/cuentas/1").contentType(MediaType.APPLICATION_JSON))
        // Then
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.persona").value("Andrés"))
        .andExpect(jsonPath("$.saldo").value("1000"));

    verify(cuentaService).findById(1L);
  }

  @Test
  void testTransferir() throws Exception {

    // Given
    TransaccionDTO dto = new TransaccionDTO();
    dto.setCuentaOrigenId(1L);
    dto.setCuentaDestinoId(2L);
    dto.setMonto(new BigDecimal("100"));
    dto.setBancoId(1L);

    // When
    mvc.perform(post("/api/cuentas/transferir")
    .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(dto)))

    // Then
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.date").value(LocalDate.now().toString()))
        .andExpect(jsonPath("$.status").value("OK"))
        .andExpect(jsonPath("$.mensaje").value("Transferencia realizada con éxito"))
        .andExpect(jsonPath("$.transacción.cuentaOrigenId").value(1L))
        .andExpect(jsonPath("$.transacción.cuentaDestinoId").value(2L))
        .andExpect(jsonPath("$.transacción.monto").value("100"))
        .andExpect(jsonPath("$.transacción.bancoId").value(1L));
  }
}