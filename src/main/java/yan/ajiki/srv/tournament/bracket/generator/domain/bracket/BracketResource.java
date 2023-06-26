package yan.ajiki.srv.tournament.bracket.generator.domain.bracket;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bracket")
public class BracketResource {

    @GetMapping("/health")
    public String healthCheck() {
        return "ok";
    }

}
