package dev.tpjava.colleguesapi.controller.handler;


import dev.tpjava.colleguesapi.controller.exception.CollegueNonTrouveException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CollegueNonTrouveExceptionHandler {
    @ExceptionHandler(value = {CollegueNonTrouveException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException e, WebRequest req) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Collegue non trouvé");
    }
}
