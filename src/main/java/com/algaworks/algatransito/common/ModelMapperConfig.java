package com.algaworks.algatransito.common;

import com.algaworks.algatransito.api.model.VeiculoModel;
import com.algaworks.algatransito.domain.model.Veiculo;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// a classe ModelMapperConfig é uma classe de configuração do Spring
// ela é responsável por criar um bean do ModelMapper
// um bean é um objeto que o Spring gerencia
@Configuration
public class ModelMapperConfig {

  // o método modelMapper() é um bean que retorna uma instância de ModelMapper
  // o Spring gerencia essa instância e a disponibiliza para outras classes
  @Bean
  public ModelMapper modelMapper() {

       var modelMapper = new ModelMapper();

       // o método createTypeMap() cria um mapeamento entre duas classes
       // o método addMappings() permite adicionar mapeamentos personalizados
       // o método map() permite criar um mapeamento personalizado

       modelMapper.createTypeMap(Veiculo.class, VeiculoModel.class)
               .addMappings(mapper -> mapper.map(Veiculo::getPlaca, VeiculoModel::setNumeroPlaca))
               .addMappings(mapper -> mapper.map(veiculo -> veiculo.getProprietario().getNome(), VeiculoModel::setNomeProprietario));


       return modelMapper;
       
  }

}
