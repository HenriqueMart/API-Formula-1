package com.ifba.api.formula1.insfraestruture.exception;

import java.io.Serial;

public class BusinessException extends RuntimeException { // Exceção personalizada para regras de negócio

    @Serial
    private static final long serialVersionUID = 1L; // Garante compatibilidade na serialização da classe

    public BusinessException(String mensagem) {
        super(mensagem); // Construtor que recebe uma mensagem de erro
    }

    public BusinessException(final Throwable cause) {
        super(cause); // Construtor que recebe a causa da exceção
    }

    public BusinessException(final String mensagem, final Throwable cause) {
        super(mensagem, cause); // Construtor que recebe mensagem e causa da exceção
    }
}
