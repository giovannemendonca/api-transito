package com.algaworks.algatransito.domain.service;

import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
public class RegistroProprietarioService {

  private final ProprietarioRepository proprietarioRepository;

  public Proprietario buscar(Long proprietarioId) {
    return proprietarioRepository.findById(proprietarioId)
            .orElseThrow(() -> new NegocioException("Proprietário não encontrado."));
  }

  @Transactional
  public Proprietario salvar( Proprietario proprietario ) {

    boolean proprietarioExistente = proprietarioRepository.findByEmail(proprietario.getEmail())
            .filter(proprietarioExistenteNaBase -> !proprietarioExistenteNaBase.equals(proprietario))
            .isPresent();


    if(proprietarioExistente){
      throw new NegocioException("Já existe um proprietário cadastrado com este Email.");
    }


    return proprietarioRepository.save( proprietario );
  }

  @Transactional
  public void excluir( Long proprietarioId ) {
    proprietarioRepository.deleteById( proprietarioId );
  }
  
}
