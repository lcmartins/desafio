package com.movimentacaobancaria.UseCase;

public class PaymentSourceException extends RuntimeException {
    private static final String ERROR_READING_DATASOURCE = "Error reading datasource";
    private String message;
    private String friendlyMessage;

    public PaymentSourceException(String message) {
        super(ERROR_READING_DATASOURCE);
        this.message = ERROR_READING_DATASOURCE + "; original error: " + message;
        this.friendlyMessage = "Erro lendo datasource, check arquivos, APIs e outros recursos";
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFriendlyMessage() {
        return friendlyMessage;
    }

    public void setFriendlyMessage(String friendlyMessage) {
        this.friendlyMessage = friendlyMessage;
    }
}
