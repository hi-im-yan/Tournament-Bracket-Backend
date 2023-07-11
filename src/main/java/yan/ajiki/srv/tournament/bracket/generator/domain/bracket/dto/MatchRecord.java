package yan.ajiki.srv.tournament.bracket.generator.domain.bracket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MatchRecord(@JsonProperty("team-1-name") String team1,
                          @JsonProperty("team-2-name") String team2,
                          @JsonProperty("team-1-score") int team1Score,
                          @JsonProperty("team-2-score") int team2Score) {
}
