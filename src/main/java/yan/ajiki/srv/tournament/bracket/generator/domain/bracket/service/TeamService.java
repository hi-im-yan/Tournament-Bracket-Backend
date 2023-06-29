package yan.ajiki.srv.tournament.bracket.generator.domain.bracket.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yan.ajiki.srv.tournament.bracket.generator.domain.bracket.dto.TeamRecord;
import yan.ajiki.srv.tournament.bracket.generator.domain.bracket.exception.NotFoundException;
import yan.ajiki.srv.tournament.bracket.generator.domain.bracket.mapper.TeamMapper;
import yan.ajiki.srv.tournament.bracket.generator.domain.bracket.repository.TeamRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/team")
public class TeamService {

    private final TeamRepository repository;
    private final TeamMapper mapper;

    public TeamService(final TeamRepository repository, final TeamMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public TeamRecord create(final TeamRecord record) {
        var model = this.mapper.toNewModel(record);

        var saved = this.repository.save(model);

        return this.mapper.toRecord(saved);
    }

    public List<TeamRecord> read(final String name) {
        var Teams = this.repository.findAllByNameContainingIgnoreCaseOrNameIsNotNull(name);

        return Teams.stream().map(this.mapper::toRecord).collect(Collectors.toList());
    }

    public TeamRecord update(final TeamRecord record) {
        var model = this.mapper.toModel(record);

        var saved = this.repository.save(model);

        return this.mapper.toRecord(saved);
    }

    public void delete(final Long id) {
        this.repository.findById(id).ifPresentOrElse(this.repository::delete,
                () -> {throw new NotFoundException("Team not found with id=" + id); });
    }
}
