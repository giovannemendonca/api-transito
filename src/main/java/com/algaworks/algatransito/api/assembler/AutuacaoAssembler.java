package com.algaworks.algatransito.api.assembler;

import com.algaworks.algatransito.api.model.AutuacaoModel;
import com.algaworks.algatransito.api.model.input.AutuacaoInput;
import com.algaworks.algatransito.domain.model.Autuacao;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AutuacaoAssembler {

  private ModelMapper modelMapper;

  // o toModel é um método que converte um Veiculo em um VeiculoModel
  public AutuacaoModel toModel( Autuacao autuacao) {
    return modelMapper.map(autuacao, AutuacaoModel.class);
  }

  // o toCollectionModel é um método que converte uma lista de Veiculo em uma lista de VeiculoModel
  public List<AutuacaoModel> toCollectionModel(List<Autuacao> autuacoes) {
    return autuacoes.stream()
            .map(this::toModel)
            .toList();
  }

  // o toEntity é um método que converte um VeiculoInput em um Veiculo
  public Autuacao toEntity( AutuacaoInput autuacaoInput) {
    return modelMapper.map(autuacaoInput, Autuacao.class);
  }

  
}
