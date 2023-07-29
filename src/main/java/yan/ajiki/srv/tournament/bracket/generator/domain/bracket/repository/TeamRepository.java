package yan.ajiki.srv.tournament.bracket.generator.domain.bracket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yan.ajiki.srv.tournament.bracket.generator.domain.bracket.model.Team;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> findAllByNameContainingIgnoreCaseOrNameIsNotNull(String name);
}