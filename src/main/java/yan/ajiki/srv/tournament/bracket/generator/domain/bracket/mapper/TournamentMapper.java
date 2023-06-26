package yan.ajiki.srv.tournament.bracket.generator.domain.bracket.mapper;

import org.springframework.stereotype.Component;
import yan.ajiki.srv.tournament.bracket.generator.domain.bracket.dto.TournamentRecord;
import yan.ajiki.srv.tournament.bracket.generator.domain.bracket.model.Tournament;

@Component
public class TournamentMapper {

    public Tournament toNewModel(final TournamentRecord record) {
        return new Tournament(null, record.name());
    }

    public Tournament toModel(final TournamentRecord record) {
        return new Tournament(record.id(), record.name());
    }

    public TournamentRecord toRecord(final Tournament model) {
        return new TournamentRecord(model.getId(), model.getName());
    }
}
