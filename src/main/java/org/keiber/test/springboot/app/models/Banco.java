package org.keiber.test.springboot.app.models;

public class Banco {
  private Long id;
  private String nombre;

  //cuando se usa un tipo int (primitivo : diferente de Integer) es para que se inicie en CERO
  private int totalTransferencias;

  public Banco() {
  }

  public Banco(Long id, String nombre, int totalTransferencias) {
    this.id = id;
    this.nombre = nombre;
    this.totalTransferencias = totalTransferencias;
  }

  // Getters and Setters

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getTotalTransferencias() {
    return totalTransferencias;
  }

  public void setTotalTransferencias(int totalTransferencias) {
    this.totalTransferencias = totalTransferencias;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
}
