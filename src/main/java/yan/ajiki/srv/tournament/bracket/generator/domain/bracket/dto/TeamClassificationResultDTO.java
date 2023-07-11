package yan.ajiki.srv.tournament.bracket.generator.domain.bracket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TeamClassificationResultDTO {

    int victoryCounter;
    int defeatCounter;
    int pointsMade;
    int pointsSuffered;
    int drawCounter;

    public TeamClassificationResultDTO() {
        this.victoryCounter = 0;
        this.defeatCounter = 0;
        this.pointsMade = 0;
        this.pointsSuffered = 0;
        this.drawCounter = 0;
    }
}
