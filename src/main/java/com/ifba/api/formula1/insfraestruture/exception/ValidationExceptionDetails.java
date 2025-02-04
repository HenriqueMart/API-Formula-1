package br.com.ifba.infrastructure.exception;

import java.time.LocalDateTime;

public class ValidationExceptionDetails {

    // Atributos para armazenar informações sobre a exceção
    private LocalDateTime timestamp; // Data e hora do erro
    private int status;              // Código de status HTTP (ex: 400 - Bad Request)
    private String title;            // Título do erro
    private String details;          // Mensagem detalhada do erro
    private String developerMessage; // Mensagem técnica para desenvolvedores
    private String fields;           // Campos que causaram o erro
    private String fieldsMessage;    // Mensagens de erro associadas aos campos

    // Construtor privado para garantir o uso do Builder
    private ValidationExceptionDetails(Builder builder) {
        this.timestamp = builder.timestamp;
        this.status = builder.status;
        this.title = builder.title;
        this.details = builder.details;
        this.developerMessage = builder.developerMessage;
        this.fields = builder.fields;
        this.fieldsMessage = builder.fieldsMessage;
    }

    // Métodos getters para acessar os atributos
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public String getDetails() {
        return details;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public String getFields() {
        return fields;
    }

    public String getFieldsMessage() {
        return fieldsMessage;
    }

    // Classe interna Builder para facilitar a criação de objetos
    public static class Builder {
        private LocalDateTime timestamp;
        private int status;
        private String title;
        private String details;
        private String developerMessage;
        private String fields;
        private String fieldsMessage;

        // Métodos para definir valores dos atributos e retornar o Builder atualizado
        public Builder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder details(String details) {
            this.details = details;
            return this;
        }

        public Builder developerMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public Builder fields(String fields) {
            this.fields = fields;
            return this;
        }

        public Builder fieldsMessage(String fieldsMessage) {
            this.fieldsMessage = fieldsMessage;
            return this;
        }

        // Método para construir e retornar uma instância de ValidationExceptionDetails
        public ValidationExceptionDetails build() {
            return new ValidationExceptionDetails(this);
        }
    }

    // Método estático para iniciar o Builder
    public static Builder builder() {
        return new Builder();
    }
}
