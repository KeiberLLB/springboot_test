package org.keiber.test.springboot.app.controllers;

import org.keiber.test.springboot.app.models.Cuenta;
import org.keiber.test.springboot.app.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/api/cuentas")
public class CuentaController {

  @Autowired
  private CuentaService cuentaService;
  
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Cuenta detalle(@PathVariable Long id) {
    return cuentaService.findById(id);
  }

  @PostMapping("/transferir")
  public ResponseEntity<?> transferir() {
      //TODO: process POST request
      
      return null;
  }
  
  
}
