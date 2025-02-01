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

@RestControllerAdvice
public class ApiExceptionHandler {

    @Value(value = "${server.error.include-exception}")
    private boolean printStackTrace;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<br.com.ifba.infrastructure.exception.ValidationExceptionDetails> handleMethodArgumentNotValid(
            MethodArgumentNotValidException methodArgumentNotValidException) {

        List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();

        // Coletando os campos e as mensagens de erro
        String fields = fieldErrors.stream()
                .map(FieldError::getField)
                .collect(Collectors.joining(", "));

        String fieldMessages = fieldErrors.stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        // Construindo o objeto de resposta
        br.com.ifba.infrastructure.exception.ValidationExceptionDetails errorDetails = br.com.ifba.infrastructure.exception.ValidationExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value()) // Usando HttpStatus.BAD_REQUEST.value() para o status 400
                .title("Erro de Validação")
                .details("Por favor, verifique os campos com erros.")
                .developerMessage(methodArgumentNotValidException.getClass().getName()) // Mensagem técnica
                .fields(fields) // Campos com erro
                .fieldsMessage(fieldMessages) // Mensagens de erro associadas aos campos
                .build();

        // Retornando a resposta com status 400 (Bad Request)
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}