package pe.edu.unsa.gestortorneos.presentation.exception;

import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pe.edu.unsa.gestortorneos.domain.exception.BusinessConflictException;
import pe.edu.unsa.gestortorneos.domain.exception.DomainException;
import pe.edu.unsa.gestortorneos.domain.exception.ResourceNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> notFound(ResourceNotFoundException e) { return error(HttpStatus.NOT_FOUND, e.getMessage()); }
    @ExceptionHandler({BusinessConflictException.class, DataIntegrityViolationException.class})
    public ResponseEntity<Map<String, Object>> conflict(Exception e) {
        String message = e instanceof DataIntegrityViolationException ? "Conflicto con datos existentes" : e.getMessage();
        return error(HttpStatus.CONFLICT, message);
    }
    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Map<String, Object>> invalidDomain(DomainException e) { return error(HttpStatus.BAD_REQUEST, e.getMessage()); }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> invalidRequest(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldErrors().stream().findFirst()
            .map(f -> f.getField() + ": " + f.getDefaultMessage()).orElse("Solicitud inválida");
        return error(HttpStatus.BAD_REQUEST, message);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> unreadable(HttpMessageNotReadableException e) {
        return error(HttpStatus.BAD_REQUEST, "El cuerpo de la solicitud es inválido");
    }
    private ResponseEntity<Map<String, Object>> error(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(Map.of("status", status.value(), "message", message, "timestamp", LocalDateTime.now().toString()));
    }
}
