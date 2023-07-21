package com.algaworks.algatransito.api.assembler;

import com.algaworks.algatransito.api.model.VeiculoModel;
import com.algaworks.algatransito.domain.model.Veiculo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

// o @AllArgsConstructor é do Lombok e cria um construtor com todos os atributos da classe
// o @Component é do Spring e indica que a classe é um bean gerenciado pelo Spring
@AllArgsConstructor
@Component
public class VeiculoAssembler {

  private final ModelMapper modelMapper;

  // o toModel é um método que converte um Veiculo em um VeiculoModel
  public VeiculoModel toModel( Veiculo veiculo) {
    return modelMapper.map(veiculo, VeiculoModel.class);
  }

  // o toCollectionModel é um método que converte uma lista de Veiculo em uma lista de VeiculoModel
  // o stream() é um método que retorna um fluxo de dados da lista
  // o map() é um método que mapeia cada elemento do fluxo de dados
  // o toList() é um método que retorna uma lista com os elementos do fluxo de dados
  public List<VeiculoModel> toCollectionModel(List<Veiculo> veiculos) {
    return veiculos.stream()
            .map(this::toModel)
            .toList();
  }

}
