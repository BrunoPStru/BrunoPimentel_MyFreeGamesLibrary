package br.inatel.myfreegameslibrary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class GameAlredyExistsException extends ResponseStatusException {
    public GameAlredyExistsException() {

        super(HttpStatus.BAD_REQUEST, String.format("Game alredy exists."));

    }
}

