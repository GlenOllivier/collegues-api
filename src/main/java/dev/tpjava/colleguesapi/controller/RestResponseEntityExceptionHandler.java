package dev.tpjava.colleguesapi.controller;
import dev.tpjava.colleguesapi.exception.CollegueInvalideException;
import dev.tpjava.colleguesapi.exception.CollegueNonTrouveException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
    @ExceptionHandler(value = {CollegueInvalideException.class})
    protected ResponseEntity<Object> handleConflict(CollegueInvalideException e, WebRequest req) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Collegue invalide");
    }

    @ExceptionHandler(value = {CollegueNonTrouveException.class})
    protected ResponseEntity<Object> handleConflict(CollegueNonTrouveException e, WebRequest req) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Collegue non trouvé");
    }
}
