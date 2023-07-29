package yan.ajiki.srv.tournament.bracket.generator.domain.bracket.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import yan.ajiki.srv.tournament.bracket.generator.domain.bracket.exception.NotFoundException;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Problem> notFoundHandler(final NotFoundException ex) {
        var problem = new Problem(HttpStatus.NOT_FOUND.value(), "NOT FOUND", ex.getMessage(), null);

        return new ResponseEntity<>(problem, HttpStatus.NOT_FOUND);
    }
}
