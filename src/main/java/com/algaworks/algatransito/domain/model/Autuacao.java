package com.algaworks.algatransito.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

// o Entity serve para indicar que a classe é uma entidade do banco de dados.
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Autuacao {

  // O @EqualsAndHashCode(onlyExplicitlyIncluded = true) serve para indicar que o Lombok só deve
  // considerar o atributo id para gerar o equals e o hashCode.
  // O @GeneratedValue serve para indicar que o valor do id será gerado automaticamente pelo banco de dados.
  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // O @ManyToOne serve para indicar que muitas autuações podem estar relacionadas a um veículo.
  @ManyToOne
  private Veiculo veiculo;
  
  private String descricao;
  private BigDecimal valorMulta;
  private OffsetDateTime dataOcorrencia;
}
