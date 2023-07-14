package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.domain.model.Proprietario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ProprietarioController {

  @GetMapping("/proprietarios")
  public List<Proprietario> listar() {
    Proprietario proprietario1 = new Proprietario();

    proprietario1.setId(1L);
    proprietario1.setName("João da Silva");
    proprietario1.setEmail("Giovanne@gmail.com");
    proprietario1.setTelefone("999999999");

    Proprietario proprietario2 = new Proprietario();
    proprietario2.setId(2L);
    proprietario2.setName("Maria da Silva");
    proprietario2.setEmail("Giovanne@gmail.com");
    proprietario2.setTelefone("999999999");

    return Arrays.asList(proprietario1, proprietario2);

  }

}
