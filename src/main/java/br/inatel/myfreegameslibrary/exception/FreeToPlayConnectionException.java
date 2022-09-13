package br.inatel.myfreegameslibrary.exception;

public class FreeToPlayConnectionException extends RuntimeException {
    public FreeToPlayConnectionException(String freeToPlayBaseUrl) {

        super(String.format("Was not possible to communicate with Free To Play at location %s", freeToPlayBaseUrl));

    }
}
