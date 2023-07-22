package com.algaworks.algatransito.domain.service;

import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.StatusVeiculo;
import com.algaworks.algatransito.domain.model.Veiculo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ApreensaoVeiculoService {

  private RegistroVeiculoService registroVeiculoService;

  @Transactional
  public void apreender(Long veiculoId) {
    Veiculo veiculo = registroVeiculoService.buscar(veiculoId);

    if(StatusVeiculo.APREENDIDO.equals(veiculo.getStatus())) {
      throw new NegocioException("Veículo já está apreendido");
    }

    veiculo.apreender();
  }

  @Transactional
  public void removerApreensao(Long veiculoId) {
    Veiculo veiculo = registroVeiculoService.buscar(veiculoId);
    
    veiculo.removerApreencao();
  }
}
