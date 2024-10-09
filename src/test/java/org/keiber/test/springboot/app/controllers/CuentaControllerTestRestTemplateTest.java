package org.keiber.test.springboot.app.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.keiber.test.springboot.app.models.TransaccionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CuentaControllerTestRestTemplateTest {

  @LocalServerPort
    private int port;

  @Autowired
  private RestTemplate client;

  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    objectMapper = new ObjectMapper();
  }

  @Test
  @Order(1)
  void testTransferir() {
    // Given
    TransaccionDTO dto = new TransaccionDTO();
    dto.setMonto(new BigDecimal("100"));
    dto.setCuentaOrigenId(1L);
    dto.setCuentaDestinoId(2L);
    dto.setBancoId(1L);

    String url = "http://localhost:" + port + "/api/cuentas/transferir";

    ResponseEntity<String> response = client
        .postForEntity(url, dto, String.class);
    String json = response.getBody();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
    assertNotNull(json);
    assertTrue(json.contains("Transferencia realizada con éxito"));

  }
}
