package org.keiber.test.springboot.app.controllers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.keiber.test.springboot.app.models.Cuenta;
import org.keiber.test.springboot.app.models.TransaccionDTO;
import org.keiber.test.springboot.app.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

  @Autowired
  private CuentaService cuentaService;

  @Operation(summary = "Obtiene el detalle de una cuenta por ID")
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Cuenta detalle(@PathVariable Long id) {
    return cuentaService.findById(id);
  }

  @Operation(summary = "Realiza una transferencia entre cuentas")
  @PostMapping("/transferir")
  public ResponseEntity<?> transferir(@RequestBody TransaccionDTO dto) {
    cuentaService.transferir(dto.getCuentaOrigenId(),
        dto.getCuentaDestinoId(),
        dto.getMonto(),
        dto.getBancoId());

    Map<String, Object> response = new HashMap<>();
    response.put("date", LocalDate.now().toString());
    response.put("status", "OK");
    response.put("mensaje", "Transferencia realizada con éxito");
    response.put("transacción", dto);

    return ResponseEntity.ok(response);
  }

}
