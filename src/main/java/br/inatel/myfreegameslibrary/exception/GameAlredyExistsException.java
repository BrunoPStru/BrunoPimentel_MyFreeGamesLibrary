package br.inatel.myfreegameslibrary.exception;

public class GameAlredyExistsException extends RuntimeException {
    public GameAlredyExistsException() {

        super(String.format("Game alredy exists."));

    }
}

