package yan.ajiki.srv.tournament.bracket.generator.domain.bracket.dto;

public record ClassificationRankingRecord(String teamName,
                            int victoryCounter,
                            int defeatCounter,
                            int pointsMade,
                            int pointsSuffered,
                            int pointsBalance) {

}