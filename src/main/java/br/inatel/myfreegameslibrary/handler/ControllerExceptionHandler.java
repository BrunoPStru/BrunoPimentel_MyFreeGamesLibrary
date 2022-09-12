package br.inatel.myfreegameslibrary.handler;

import br.inatel.myfreegameslibrary.exception.GameAlredyExistsException;
import br.inatel.myfreegameslibrary.exception.FreeToPlayConnectionException;
import br.inatel.myfreegameslibrary.exception.GameNotFoundException;
import br.inatel.myfreegameslibrary.model.rest.Error;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
@ResponseBody
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(GameNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Error gameNotFoundException(GameNotFoundException gameNotFoundException){
        return Error.builder()
                .httpStatusCode(HttpStatus.NOT_FOUND)
                .message(gameNotFoundException.getMessage())
                .build();
    }

    @ExceptionHandler(FreeToPlayConnectionException.class)
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    public Error freeToPlayConnectionException(FreeToPlayConnectionException freeToPlayConnectionException){
        return Error.builder()
                .httpStatusCode(HttpStatus.SERVICE_UNAVAILABLE)
                .message(freeToPlayConnectionException.getMessage())
                .build();
    }

    @ExceptionHandler(GameAlredyExistsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Error gameAlredyExistsException(GameAlredyExistsException gameAlredyExistsException){
        return Error.builder()
                .httpStatusCode(HttpStatus.BAD_REQUEST)
                .message(gameAlredyExistsException.getMessage())
                .build();
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Error numberFormatException(NumberFormatException numberFormatException){
        return Error.builder()
                .httpStatusCode(HttpStatus.BAD_REQUEST)
                .message(numberFormatException.getMessage())
                .build();
    }

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Error badRequest(HttpClientErrorException.BadRequest badRequest){
        return Error.builder()
                .httpStatusCode(HttpStatus.BAD_REQUEST)
                .message(badRequest.getMessage())
                .build();
    }

    @ExceptionHandler(JDBCConnectionException.class)
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    public Error jdbcConnectionException(JDBCConnectionException jdbcConnectionException){
        return Error.builder()
                .httpStatusCode(HttpStatus.SERVICE_UNAVAILABLE)
                .message(jdbcConnectionException.getMessage())
                .build();
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    public Error nullPointerException(NullPointerException nullPointerException){
        return Error.builder()
                .httpStatusCode(HttpStatus.SERVICE_UNAVAILABLE)
                .message(nullPointerException.getMessage())
                .build();
    }
}
