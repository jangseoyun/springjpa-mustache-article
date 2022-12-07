package hospitaljpa.mustache.exception;

import hospitaljpa.mustache.domain.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeExceptionHandler(HospitalReviewException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Response.error(e.getMessage()));
    }

    @ExceptionHandler(HospitalReviewException.class)
    public ResponseEntity<?> hospitalReviewAppExceptionHandler(HospitalReviewException e) {
        return ResponseEntity
                .status(e.getErrorcode().getStatus())
                .body(Response.error(e.getErrorcode().getMessage()));
    }

}
