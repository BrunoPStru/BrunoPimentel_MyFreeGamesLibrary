package br.inatel.myfreegameslibrary.model.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Builder
public class Error {

    private HttpStatus httpStatus;

    private String message;
}
