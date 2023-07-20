package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;
import com.algaworks.algatransito.domain.service.RegistroProprietarioService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/proprietarios")
public class ProprietarioController {

  private final ProprietarioRepository proprietarioRepository;
  private final RegistroProprietarioService registroProprietarioService;

  @GetMapping
  public List<Proprietario> listar() {
    return proprietarioRepository.findAll();
  }

  @GetMapping("/{proprietarioId}")
  public ResponseEntity<Proprietario> buscar( @PathVariable Long proprietarioId) {
    return proprietarioRepository.findById(proprietarioId)
            .map(proprietario -> ResponseEntity.ok(proprietario))
            .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Proprietario adicionar(@Valid @RequestBody Proprietario proprietario) {
    return registroProprietarioService.salvar(proprietario);
  }

  @PutMapping("/{proprietarioId}")
  public ResponseEntity<Proprietario> atualizar( @PathVariable Long proprietarioId,@Valid @RequestBody Proprietario proprietario){

    if(!proprietarioRepository.existsById(proprietarioId)){
      return ResponseEntity.notFound().build();
    }
    proprietario.setId(proprietarioId);
    Proprietario proprietarioAtualizado = registroProprietarioService.salvar(proprietario);

    return ResponseEntity.ok(proprietarioAtualizado);
  }

  @DeleteMapping("/{proprietarioId}")
  public ResponseEntity<Void> remover(@PathVariable Long proprietarioId){
    if(!proprietarioRepository.existsById(proprietarioId)){
      return ResponseEntity.notFound().build();
    }
    registroProprietarioService.excluir(proprietarioId);
    return ResponseEntity.noContent().build();
  }
}
