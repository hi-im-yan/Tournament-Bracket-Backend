package yan.ajiki.srv.tournament.bracket.generator.domain.bracket.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
