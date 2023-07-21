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

// A classe ProprietarioController é um controlador REST

@AllArgsConstructor
@RestController
@RequestMapping("/proprietarios")
public class ProprietarioController {

  private final ProprietarioRepository proprietarioRepository;
  private final RegistroProprietarioService registroProprietarioService;

  // o @GetMapping é do Spring e já inclui o @RequestMapping com o método GET
  @GetMapping
  public List<Proprietario> listar() {
    return proprietarioRepository.findAll();
  }

  // o @GetMapping é do Spring e já inclui o @RequestMapping com o método GET e o parâmetro {proprietarioId}
  @GetMapping("/{proprietarioId}")
  public ResponseEntity<Proprietario> buscar( @PathVariable Long proprietarioId) {
    return proprietarioRepository.findById(proprietarioId)
            .map(proprietario -> ResponseEntity.ok(proprietario))
            .orElse(ResponseEntity.notFound().build());
  }

  // o @PostMapping é do Spring e já inclui o @RequestMapping com o método POST
  // o HttpStatus.CREATED é do Spring é um atalho para o código de status 201
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Proprietario adicionar(@Valid @RequestBody Proprietario proprietario) {
    return registroProprietarioService.salvar(proprietario);
  }

  // o @PutMapping é do Spring e já inclui o @RequestMapping com o método PUT e o parâmetro {proprietarioId}
  @PutMapping("/{proprietarioId}")
  public ResponseEntity<Proprietario> atualizar( @PathVariable Long proprietarioId,@Valid @RequestBody Proprietario proprietario){

    if(!proprietarioRepository.existsById(proprietarioId)){
      return ResponseEntity.notFound().build();
    }
    proprietario.setId(proprietarioId);
    Proprietario proprietarioAtualizado = registroProprietarioService.salvar(proprietario);

    return ResponseEntity.ok(proprietarioAtualizado);
  }

  // o @DeleteMapping é do Spring e já inclui o @RequestMapping com o método DELETE e o parâmetro {proprietarioId}
  @DeleteMapping("/{proprietarioId}")
  public ResponseEntity<Void> remover(@PathVariable Long proprietarioId){
    if(!proprietarioRepository.existsById(proprietarioId)){
      return ResponseEntity.notFound().build();
    }
    registroProprietarioService.excluir(proprietarioId);
    return ResponseEntity.noContent().build();
  }
}
