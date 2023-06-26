package yan.ajiki.srv.tournament.bracket.generator.domain.bracket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yan.ajiki.srv.tournament.bracket.generator.domain.bracket.model.Tournament;

import java.util.List;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    List<Tournament> findAllByNameContainingIgnoreCaseOrNameIsNotNull(String name);
}