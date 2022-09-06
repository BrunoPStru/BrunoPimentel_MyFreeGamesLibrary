package br.inatel.myfreegameslibrary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class GameNotFoundException extends ResponseStatusException {
    public GameNotFoundException(String title) {

        super(HttpStatus.NOT_FOUND, String.format("Game %s was not found.", title));

    }
}

