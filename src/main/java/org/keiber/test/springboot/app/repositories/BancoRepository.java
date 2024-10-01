package org.keiber.test.springboot.app.repositories;

import org.keiber.test.springboot.app.models.Banco;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BancoRepository {
  List<Banco> findAll();

  Banco findById(Long id);

  void update(Banco banco);
}
