package com.algaworks.algatransito.api.exceptionhandler;

import com.algaworks.algatransito.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algatransito.domain.exception.NegocioException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.Map;
import java.util.stream.Collectors;

// a classe ResponseEntityExceptionHandler é uma classe do Spring que já trata as exceções
// de forma mais genérica, porém, ela não retorna o corpo da mensagem, por isso,
// é necessário criar uma classe que herda dela e sobrescrever o método handleExceptionInternal

@AllArgsConstructor
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  private final MessageSource messageSource;

  // o método handleExceptionInternal é chamado quando ocorre uma exceção
  // ele recebe como parâmetro a exceção, o corpo da mensagem, os headers, o status e a requisição
  @Override
  protected ResponseEntity <Object> handleMethodArgumentNotValid( MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request ) {

    // o método ProblemDetail.forStatus cria um objeto do tipo ProblemDetail
    ProblemDetail problemDetail = ProblemDetail.forStatus(status);

    problemDetail.setTitle("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.");
    problemDetail.setType(URI.create("https://algaTransito.com.br/dados-invalidos"));

      Map <String, String> fields =  ex.getBindingResult().getAllErrors()
            .stream()
            .collect(Collectors.toMap(
                    objectError -> ((FieldError) objectError).getField(),
                    objectError -> messageSource.getMessage(objectError, LocaleContextHolder.getLocale())
            ));

     problemDetail.setProperty("fields", fields);

   return handleExceptionInternal(ex, problemDetail, headers, status, request);
  }




  @ExceptionHandler(NegocioException.class)
  public ProblemDetail handlerNegocio( NegocioException e ){
    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

    problemDetail.setTitle(e.getMessage());
    problemDetail.setType(URI.create("https://algaTransito.com.br/regra-de-negocio"));
    

    return problemDetail;
  }

  @ExceptionHandler(EntidadeNaoEncontradaException.class)
  public ProblemDetail handlerNegocio( EntidadeNaoEncontradaException e ){
    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

    problemDetail.setTitle(e.getMessage());
    problemDetail.setType(URI.create("https://algaTransito.com.br/nao-encontrado"));


    return problemDetail;
  }


  @ExceptionHandler(DataIntegrityViolationException.class)
  public ProblemDetail handlerDataIntegrityViolationException( DataIntegrityViolationException e ) {

    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);

    problemDetail.setTitle("Recurso em uso. Não pode ser removido.");
    problemDetail.setType(URI.create("https://algaTransito.com.br/recurso-em-uso"));

    return problemDetail;
  }
}

