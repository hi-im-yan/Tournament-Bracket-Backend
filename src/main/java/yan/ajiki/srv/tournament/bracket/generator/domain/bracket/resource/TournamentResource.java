package yan.ajiki.srv.tournament.bracket.generator.domain.bracket.resource;

import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yan.ajiki.srv.tournament.bracket.generator.domain.bracket.dto.MatchRecord;
import yan.ajiki.srv.tournament.bracket.generator.domain.bracket.dto.ClassificationRankingRecord;
import yan.ajiki.srv.tournament.bracket.generator.domain.bracket.dto.TournamentRecord;
import yan.ajiki.srv.tournament.bracket.generator.domain.bracket.service.TournamentService;

import java.util.List;

@RestController
@RequestMapping("/tournament")
public class TournamentResource {

    private final TournamentService service;

    public TournamentResource(final TournamentService service) {
        this.service = service;
    }

    @PostMapping
    public TournamentRecord create(final @RequestBody TournamentRecord record) {
        System.out.println(record);
        return this.service.create(record);
    }

    @GetMapping
    public List<TournamentRecord> read(final @RequestParam(required = false) String name) {
        return this.service.read(name);
    }

    @PutMapping
    public TournamentRecord update(final @RequestBody TournamentRecord record) {
        return this.service.update(record);
    }

    @DeleteMapping("/{id}")
    public void delete(final @PathParam("id") Long id) {
        this.service.delete(id);
    }

    @CrossOrigin("http://localhost:3000")
    @PostMapping("/classification-phase")
    public ResponseEntity<List<ClassificationRankingRecord>> classificationPhase(@RequestBody List<MatchRecord> matches) {
        var classificationRankingRecords = this.service.calculateClassificationRank(matches);

        return ResponseEntity.ok(classificationRankingRecords);
    }
}
