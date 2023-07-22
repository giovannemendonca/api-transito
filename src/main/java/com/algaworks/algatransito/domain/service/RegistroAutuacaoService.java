package com.algaworks.algatransito.domain.service;

import com.algaworks.algatransito.domain.model.Autuacao;
import com.algaworks.algatransito.domain.model.Veiculo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// O @Service serve para indicar que a classe é um componente Spring.
@AllArgsConstructor
@Service
public class RegistroAutuacaoService {

  private RegistroVeiculoService registroVeiculoService;

  // o @Transactional serve para indicar que o método deve ser executado dentro de uma transação.
  @Transactional
  public Autuacao registrar(Long veiculoId, Autuacao autuacao) {
    Veiculo veiculo = registroVeiculoService.buscar(veiculoId);

    return veiculo.adicionarAutuacao(autuacao);
  }

}
