package org.keiber.test.springboot.app.services;

import org.keiber.test.springboot.app.models.Banco;
import org.keiber.test.springboot.app.models.Cuenta;
import org.keiber.test.springboot.app.repositories.BancoRepository;
import org.keiber.test.springboot.app.repositories.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CuentaServiceImpl implements CuentaService {

  private final CuentaRepository cuentaRepository;
  private final BancoRepository bancoRepository;

  @Autowired
  public CuentaServiceImpl(CuentaRepository cuentaRepository, BancoRepository bancoRepository) {
    this.cuentaRepository = cuentaRepository;
    this.bancoRepository = bancoRepository;
  }

  @Override
  public Cuenta findById(Long id) {
    return cuentaRepository.findById(id).orElseThrow();
  }

  @Override
  public int revisarTotalTransferencias(Long bancoId) {
    Banco banco = bancoRepository.findById(bancoId).orElseThrow();
    return banco.getTotalTransferencias();
  }

  @Override
  public BigDecimal revisarSaldo(Long cuentaId) {
    Cuenta cuenta = cuentaRepository.findById(cuentaId).orElseThrow();
    return cuenta.getSaldo();
  }

  @Override
  public void transferir(Long numCuentaOrigen, Long numCuentaDestino, BigDecimal monto, Long bancoId) {
    Cuenta cuentaOrigen = cuentaRepository.findById(numCuentaOrigen).orElseThrow();
    cuentaOrigen.debito(monto);
    cuentaRepository.save(cuentaOrigen);

    Cuenta cuentaDestino = cuentaRepository.findById(numCuentaDestino).orElseThrow();
    cuentaDestino.credito(monto);
    cuentaRepository.save(cuentaDestino);

    Banco banco = bancoRepository.findById(bancoId).orElseThrow();
    int totalTranferencias = banco.getTotalTransferencias();
    banco.setTotalTransferencias(++totalTranferencias);
    bancoRepository.save(banco);
  }
}
