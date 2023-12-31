package com.algaworks.algatransito.domain.service;

import com.algaworks.algatransito.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.model.StatusVeiculo;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.repository.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class RegistroVeiculoService {

  private final VeiculoRepository veiculoRepository;
  private final RegistroProprietarioService registroProprietarioService;

  public Veiculo buscar( Long veiculoId ) {
    return veiculoRepository.findById( veiculoId )
            .orElseThrow(() -> new EntidadeNaoEncontradaException("Veículo não encontrado."));
  }

  @Transactional
  public Veiculo cadastrar( Veiculo veiculo ) {

    if(veiculo.getId() != null){
      throw new NegocioException("Veículo já cadastrado.");
    }

    boolean veiculoExistente = veiculoRepository.findByPlaca( veiculo.getPlaca() )
                    .filter(p -> !p.equals(veiculo))
                    .isPresent();

    if(veiculoExistente){
      throw new NegocioException("Já existe um veículo cadastrado com esta placa.");
    }

    Proprietario proprietario = registroProprietarioService.buscar(veiculo.getProprietario().getId());
    
    veiculo.setProprietario(proprietario);
    veiculo.setStatus(StatusVeiculo.REGULAR);
    veiculo.setDataCadastro(OffsetDateTime.now());
    
    return veiculoRepository.save( veiculo );
  }
  
  

}
