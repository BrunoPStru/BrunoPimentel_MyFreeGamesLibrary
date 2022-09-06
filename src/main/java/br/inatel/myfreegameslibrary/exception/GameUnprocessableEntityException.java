package br.inatel.myfreegameslibrary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class GameUnprocessableEntityException extends ResponseStatusException {

    public GameUnprocessableEntityException() {

        super(HttpStatus.UNPROCESSABLE_ENTITY, String.format("Game not found, title cannot be null or empty."));

    }

}
