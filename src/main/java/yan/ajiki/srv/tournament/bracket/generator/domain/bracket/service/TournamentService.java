package yan.ajiki.srv.tournament.bracket.generator.domain.bracket.service;

import org.springframework.stereotype.Service;
import yan.ajiki.srv.tournament.bracket.generator.domain.bracket.dto.TournamentRecord;
import yan.ajiki.srv.tournament.bracket.generator.domain.bracket.exception.NotFoundException;
import yan.ajiki.srv.tournament.bracket.generator.domain.bracket.mapper.TournamentMapper;
import yan.ajiki.srv.tournament.bracket.generator.domain.bracket.repository.TournamentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TournamentService {

    private final TournamentRepository repository;
    private final TournamentMapper mapper;

    public TournamentService(final TournamentRepository repository, final TournamentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public TournamentRecord create(final TournamentRecord record) {
        var model = this.mapper.toNewModel(record);

        var saved = this.repository.save(model);

        return this.mapper.toRecord(saved);
    }

    public List<TournamentRecord> read(final String name) {
        var tournaments = this.repository.findAllByNameContainingIgnoreCaseOrNameIsNotNull(name);

        return tournaments.stream().map(this.mapper::toRecord).collect(Collectors.toList());
    }

    public TournamentRecord update(final TournamentRecord record) {
        var model = this.mapper.toModel(record);

        var saved = this.repository.save(model);

        return this.mapper.toRecord(saved);
    }

    public void delete(final Long id) {
        this.repository.findById(id).ifPresentOrElse(this.repository::delete,
                () -> {throw new NotFoundException("Tournament not found with id=" + id); });
    }
}
