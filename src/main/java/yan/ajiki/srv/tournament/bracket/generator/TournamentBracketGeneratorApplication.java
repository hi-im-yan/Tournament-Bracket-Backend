package yan.ajiki.srv.tournament.bracket.generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TournamentBracketGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TournamentBracketGeneratorApplication.class, args);
	}

	@RequestMapping("/healthCheck")
	public ResponseEntity<Void> healthCheck() {
		return ResponseEntity.ok().build();
	}
}
