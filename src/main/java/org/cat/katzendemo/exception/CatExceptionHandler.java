package org.cat.katzendemo.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.NoSuchElementException;

@ControllerAdvice
public class CatExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorMessage> handleAccessTokenInvalidException(NoSuchElementException ex) {

        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.NO_CONTENT,
                ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("Europe/Berlin")),
                ex.getMessage(),
                "could not get entity");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(errorMessage, headers, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGenericException(Exception ex) {
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("Europe/Berlin")),
                ex.getMessage(),
                "an error occurred");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(errorMessage, headers, HttpStatus.BAD_REQUEST);
    }
}
