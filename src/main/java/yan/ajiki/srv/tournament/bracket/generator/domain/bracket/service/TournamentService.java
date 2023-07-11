package yan.ajiki.srv.tournament.bracket.generator.domain.bracket.service;

import org.springframework.stereotype.Service;
import yan.ajiki.srv.tournament.bracket.generator.domain.bracket.dto.ClassificationRankingRecord;
import yan.ajiki.srv.tournament.bracket.generator.domain.bracket.dto.TeamClassificationResultDTO;
import yan.ajiki.srv.tournament.bracket.generator.domain.bracket.dto.MatchRecord;
import yan.ajiki.srv.tournament.bracket.generator.domain.bracket.dto.TournamentRecord;
import yan.ajiki.srv.tournament.bracket.generator.domain.bracket.exception.NotFoundException;
import yan.ajiki.srv.tournament.bracket.generator.domain.bracket.mapper.TournamentMapper;
import yan.ajiki.srv.tournament.bracket.generator.domain.bracket.repository.TournamentRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public List<ClassificationRankingRecord> calculateClassificationRank(List<MatchRecord> matches) {
        var teamsFinalResults = new HashMap<String, TeamClassificationResultDTO>();

        matches.forEach(match -> {
            // team 1 results
            var team1Result = teamsFinalResults.getOrDefault(match.team1(), new TeamClassificationResultDTO());
            team1Result.setPointsMade(team1Result.getPointsMade() + match.team1Score());
            team1Result.setPointsSuffered(team1Result.getPointsSuffered() + match.team2Score());

            // team 2 results
            var team2Result = teamsFinalResults.getOrDefault(match.team2(), new TeamClassificationResultDTO());
            team2Result.setPointsMade(team2Result.getPointsMade() + match.team2Score());
            team2Result.setPointsSuffered(team2Result.getPointsSuffered() + match.team1Score());

            if (match.team1Score() > match.team2Score()) {
                team1Result.setVictoryCounter(team1Result.getVictoryCounter() + 1);
                team2Result.setDefeatCounter(team2Result.getDefeatCounter() + 1);
            }

            if (match.team2Score() > match.team1Score()) {
                team2Result.setVictoryCounter(team2Result.getVictoryCounter() + 1);
                team1Result.setDefeatCounter(team1Result.getDefeatCounter() + 1);
            }

            if (match.team1Score() == match.team2Score()) {
                team1Result.setDrawCounter(team1Result.getDrawCounter() + 1);
                team2Result.setDrawCounter(team2Result.getDrawCounter() + 1);
            }

            teamsFinalResults.put(match.team1(), team1Result);
            teamsFinalResults.put(match.team2(), team2Result);
        });

        // Create a list to store the ClassificationRankingRecord objects
        List<ClassificationRankingRecord> rankingRecords = new ArrayList<>();

        // Iterate over the teamsFinalResults map and convert the entries to ClassificationRankingRecord objects
        for (Map.Entry<String, TeamClassificationResultDTO> entry : teamsFinalResults.entrySet()) {
            String teamName = entry.getKey();
            TeamClassificationResultDTO teamResult = entry.getValue();
            int pointsBalance = teamResult.getPointsMade() - teamResult.getPointsSuffered();

            ClassificationRankingRecord rankingRecord = new ClassificationRankingRecord(
                    teamName,
                    teamResult.getVictoryCounter(),
                    teamResult.getDefeatCounter(),
                    teamResult.getPointsMade(),
                    teamResult.getPointsSuffered(),
                    pointsBalance
            );

            rankingRecords.add(rankingRecord);
        }

        // Sort the rankingRecords based on victory counter and points balance
        rankingRecords.sort((record1, record2) -> {
            int victoryComparison = Integer.compare(record2.victoryCounter(), record1.victoryCounter());
            if (victoryComparison != 0) {
                return victoryComparison;
            }

            return Integer.compare(record2.pointsBalance(), record1.pointsBalance());
        });

        return rankingRecords;

    }
}
