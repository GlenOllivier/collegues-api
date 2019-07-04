package dev.tpjava.colleguesapi.controller.handler;
import dev.tpjava.colleguesapi.controller.exception.CollegueInvalideException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.format.DateTimeParseException;

@ControllerAdvice
public class CollegueInvalideExceptionHandler {
    @ExceptionHandler(value = {CollegueInvalideException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException e, WebRequest req) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Collegue invalide");
    }
}
