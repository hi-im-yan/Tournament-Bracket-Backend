package yan.ajiki.srv.tournament.bracket.generator.domain.bracket.mapper;

import org.springframework.stereotype.Component;
import yan.ajiki.srv.tournament.bracket.generator.domain.bracket.dto.TeamRecord;
import yan.ajiki.srv.tournament.bracket.generator.domain.bracket.model.Team;

@Component
public class TeamMapper {

    public Team toNewModel(final TeamRecord record) {
        return new Team(null, record.name());
    }

    public Team toModel(final TeamRecord record) {
        return new Team(record.id(), record.name());
    }

    public TeamRecord toRecord(final Team model) {
        return new TeamRecord(model.getId(), model.getName());
    }
}
