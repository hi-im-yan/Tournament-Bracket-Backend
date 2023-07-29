package yan.ajiki.srv.tournament.bracket.generator.domain.bracket.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import yan.ajiki.srv.tournament.bracket.generator.domain.bracket.dto.MatchRecord;
import yan.ajiki.srv.tournament.bracket.generator.domain.bracket.mapper.TournamentMapper;
import yan.ajiki.srv.tournament.bracket.generator.domain.bracket.repository.TournamentRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class TournamentServiceTest {

    @Mock
    private TournamentRepository repository;

    @Mock
    private TournamentMapper mapper;

    private TournamentService service;
    @BeforeEach
    void init() {
        this.service = new TournamentService(repository, mapper);
    }
    @Test
    void calculateClassificationRank() {
        List<MatchRecord> classificationMatches = new ArrayList<MatchRecord>(Arrays.asList(
                new MatchRecord("ACB1", "ACB2", 13, 8),
                new MatchRecord("DOUD1", "ACB3", 17, 11),
                new MatchRecord("L.LIMA1", "NIPO1", 9, 16),

                new MatchRecord("NIPO1", "Q.COCO", 12, 9),
                new MatchRecord("ACB2", "L.LIMA2", 22, 7),
                new MatchRecord("ACB4", "ACB5", 6, 16),

                new MatchRecord("ACB4", "DOUD1", 14, 8),
                new MatchRecord("Q.COCO", "L.LIMA1", 14, 10),
                new MatchRecord("L.LIMA2", "ACB1", 12, 13),

                new MatchRecord("ACB1", "L.LIMA1", 13, 10),
                new MatchRecord("ACB5", "ACB3", 7, 10),
                new MatchRecord("NIPO1", "L.LIMA2", 12, 16),

                new MatchRecord("ACB2", "ACB3", 16, 8),
                new MatchRecord("DOUD1", "ACB5", 12, 16),
                new MatchRecord("Q.COCO", "ACB4", 13, 9)
        )) ;
        var finalResults = this.service.calculateClassificationRank(classificationMatches);

        assertEquals("ACB1", finalResults.get(0).teamName());
        assertEquals(3, finalResults.get(0).victoryCounter());
        assertEquals(0, finalResults.get(0).defeatCounter());
        assertEquals(39, finalResults.get(0).pointsMade());
        assertEquals(30, finalResults.get(0).pointsSuffered());
        assertEquals(9, finalResults.get(0).pointsBalance());

        assertEquals("ACB2", finalResults.get(1).teamName());
        assertEquals(2, finalResults.get(1).victoryCounter());
        assertEquals(1, finalResults.get(1).defeatCounter());
        assertEquals(46, finalResults.get(1).pointsMade());
        assertEquals(28, finalResults.get(1).pointsSuffered());
        assertEquals(18, finalResults.get(1).pointsBalance());

        assertEquals("ACB5", finalResults.get(2).teamName());
        assertEquals(2, finalResults.get(2).victoryCounter());
        assertEquals(1, finalResults.get(2).defeatCounter());
        assertEquals(39, finalResults.get(2).pointsMade());
        assertEquals(28, finalResults.get(2).pointsSuffered());
        assertEquals(11, finalResults.get(2).pointsBalance());

        assertEquals("NIPO1", finalResults.get(3).teamName());
        assertEquals(2, finalResults.get(3).victoryCounter());
        assertEquals(1, finalResults.get(3).defeatCounter());
        assertEquals(40, finalResults.get(3).pointsMade());
        assertEquals(34, finalResults.get(3).pointsSuffered());
        assertEquals(6, finalResults.get(3).pointsBalance());

        assertEquals("Q.COCO", finalResults.get(4).teamName());
        assertEquals(2, finalResults.get(4).victoryCounter());
        assertEquals(1, finalResults.get(4).defeatCounter());
        assertEquals(36, finalResults.get(4).pointsMade());
        assertEquals(31, finalResults.get(4).pointsSuffered());
        assertEquals(5, finalResults.get(4).pointsBalance());

        assertEquals("DOUD1", finalResults.get(5).teamName());
        assertEquals(1, finalResults.get(5).victoryCounter());
        assertEquals(2, finalResults.get(5).defeatCounter());
        assertEquals(37, finalResults.get(5).pointsMade());
        assertEquals(41, finalResults.get(5).pointsSuffered());
        assertEquals(-4, finalResults.get(5).pointsBalance());

        assertEquals("ACB4", finalResults.get(6).teamName());
        assertEquals(1, finalResults.get(6).victoryCounter());
        assertEquals(2, finalResults.get(6).defeatCounter());
        assertEquals(29, finalResults.get(6).pointsMade());
        assertEquals(37, finalResults.get(6).pointsSuffered());
        assertEquals(-8, finalResults.get(6).pointsBalance());

        assertEquals("ACB3", finalResults.get(7).teamName());
        assertEquals(1, finalResults.get(7).victoryCounter());
        assertEquals(2, finalResults.get(7).defeatCounter());
        assertEquals(29, finalResults.get(7).pointsMade());
        assertEquals(40, finalResults.get(7).pointsSuffered());
        assertEquals(-11, finalResults.get(7).pointsBalance());

        assertEquals("L.LIMA2", finalResults.get(8).teamName());
        assertEquals(1, finalResults.get(8).victoryCounter());
        assertEquals(2, finalResults.get(8).defeatCounter());
        assertEquals(35, finalResults.get(8).pointsMade());
        assertEquals(47, finalResults.get(8).pointsSuffered());
        assertEquals(-12, finalResults.get(8).pointsBalance());

        assertEquals("L.LIMA1", finalResults.get(9).teamName());
        assertEquals(0, finalResults.get(9).victoryCounter());
        assertEquals(3, finalResults.get(9).defeatCounter());
        assertEquals(29, finalResults.get(9).pointsMade());
        assertEquals(43, finalResults.get(9).pointsSuffered());
        assertEquals(-14, finalResults.get(9).pointsBalance());

    }
}