package br.inatel.myfreegameslibrary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AlredyExistsException extends ResponseStatusException {
    public AlredyExistsException() {

        super(HttpStatus.IM_USED, String.format("Game alredy exists."));

    }
}

