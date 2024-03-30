package com.example.MangaHaven.handler;

import com.example.MangaHaven.exception.EntityNotFoundException;
import com.example.MangaHaven.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<?> handleEntityNotFound(Exception exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    protected ResponseEntity<?> handleEntityNotFound(EntityNotFoundException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ValidationException.class)
    protected ResponseEntity<?> handleEntityNotFound(ValidationException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
