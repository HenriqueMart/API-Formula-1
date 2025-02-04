package com.ifba.api.formula1.insfraestruture.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice // Indica que esta classe trata exceções globalmente para os controllers
public class ApiExceptionHandler {

    @Value(value = "${server.error.include-exception}") // Obtém configuração para exibir stack trace
    private boolean printStackTrace;

    @ExceptionHandler(MethodArgumentNotValidException.class) // Captura exceções de validação de argumentos
    protected ResponseEntity<br.com.ifba.infrastructure.exception.ValidationExceptionDetails> handleMethodArgumentNotValid(
            MethodArgumentNotValidException methodArgumentNotValidException) {

        List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();

        // Coletando os nomes dos campos com erro
        String fields = fieldErrors.stream()
                .map(FieldError::getField)
                .collect(Collectors.joining(", "));

        // Coletando as mensagens de erro correspondentes
        String fieldMessages = fieldErrors.stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        // Criando objeto de resposta para os erros de validação
        br.com.ifba.infrastructure.exception.ValidationExceptionDetails errorDetails = br.com.ifba.infrastructure.exception.ValidationExceptionDetails.builder()
                .timestamp(LocalDateTime.now()) // Registra o momento do erro
                .status(HttpStatus.BAD_REQUEST.value()) // Código HTTP 400 (Bad Request)
                .title("Erro de Validação") // Título da resposta de erro
                .details("Por favor, verifique os campos com erros.") // Mensagem geral
                .developerMessage(methodArgumentNotValidException.getClass().getName()) // Nome da exceção técnica
                .fields(fields) // Lista dos campos inválidos
                .fieldsMessage(fieldMessages) // Mensagens de erro específicas dos campos
                .build();

        // Retorna resposta HTTP 400 com detalhes do erro
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
