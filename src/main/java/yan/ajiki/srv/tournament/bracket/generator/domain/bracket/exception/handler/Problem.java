package yan.ajiki.srv.tournament.bracket.generator.domain.bracket.exception.handler;

import java.util.Map;

public record Problem(int status, String title, String description, Map<String, String> errors) {
}
