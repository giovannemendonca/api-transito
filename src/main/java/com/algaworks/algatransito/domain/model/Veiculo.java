package com.algaworks.algatransito.domain.model;

import com.algaworks.algatransito.domain.exception.NegocioException;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Veiculo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Long id;


  @Valid
  @NotNull
  @ManyToOne
  private Proprietario proprietario;

  private String modelo;
  private String marca;
  private String placa;
  private OffsetDateTime dataCadastro;
  private OffsetDateTime dataApreensao;

  @Enumerated(EnumType.STRING)
  private StatusVeiculo status;

  // O @OneToMany serve para mapear o relacionamento de um para muitos
  // o cascade serve para que quando um veículo for adicionado, o JPA já adicione as autuações também.
  // ou seja, o cascade serve para que o JPA faça o que chamamos de cascata.
  
  @OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL)
  private List<Autuacao> autuacoes = new ArrayList <>();

  // esse método serve para adicionar uma autuação ao veículo e já setar a data da ocorrência e o veículo.
  // o método retorna a autuação adicionada.
  public Autuacao adicionarAutuacao(Autuacao autuacao) {
    autuacao.setDataOcorrencia(OffsetDateTime.now());
    autuacao.setVeiculo(this);
    getAutuacoes().add(autuacao);
    return autuacao;
  }

  private boolean isApreendido() {
    return StatusVeiculo.APREENDIDO.equals(getStatus());
  }

  public void apreender() {
    if(isApreendido()){
      throw new NegocioException("Veículo já está apreendido");
    }
    setStatus(StatusVeiculo.APREENDIDO);
    setDataApreensao(OffsetDateTime.now());
    
  }

  public void removerApreencao() {
    if(!isApreendido()){
      throw new NegocioException("Veículo não está apreendido");
    }
    setStatus(StatusVeiculo.REGULAR);
    setDataApreensao(null);
  }

}
