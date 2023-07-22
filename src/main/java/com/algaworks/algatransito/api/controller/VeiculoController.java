package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.api.assembler.VeiculoAssembler;
import com.algaworks.algatransito.api.model.VeiculoModel;
import com.algaworks.algatransito.api.model.input.VeiculoInput;
import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.repository.VeiculoRepository;
import com.algaworks.algatransito.domain.service.RegistroVeiculoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// o @AllArgsConstructor é do Lombok e cria um construtor com todos os atributos da classe
// o @RestController é do Spring e já inclui o @Controller e o @ResponseBody
// o @RequestMapping é do Spring e já inclui o @GetMapping, @PostMapping, @PutMapping e @DeleteMapping

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

  private final VeiculoRepository veiculoRepository;
  private final RegistroVeiculoService registroVeiculoService;
  private final VeiculoAssembler veiculoAssembler;


  // o @GetMapping é do Spring e já inclui o @RequestMapping com o método GET
  @GetMapping
  public List <VeiculoModel> listar() {
    return veiculoAssembler.toCollectionModel(veiculoRepository.findAll());
  }

  // o @GetMapping é do Spring e já inclui o @RequestMapping com o método GET e o parâmetro {veiculoId}
  @GetMapping("/{veiculoId}")
    public ResponseEntity<VeiculoModel> buscar( @PathVariable Long veiculoId){
    return veiculoRepository.findById(veiculoId)
            .map(veiculoAssembler::toModel)
            .map(veiculo -> ResponseEntity.ok(veiculo))
            .orElse(ResponseEntity.notFound().build());
  }

  // o @PostMapping é do Spring e já inclui o @RequestMapping com o método POST
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public VeiculoModel adicionar(@Valid @RequestBody VeiculoInput veiculo){

    Veiculo novoVeiculo = veiculoAssembler.toEntity(veiculo);
    Veiculo veiculoSalvo = registroVeiculoService.cadastrar(novoVeiculo);

    return veiculoAssembler.toModel(veiculoSalvo);
  }


  
}
