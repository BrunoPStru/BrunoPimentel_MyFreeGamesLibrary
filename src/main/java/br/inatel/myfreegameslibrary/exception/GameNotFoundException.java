package br.inatel.myfreegameslibrary.exception;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException() {

        super(String.format("Game not found."));
    }
}

