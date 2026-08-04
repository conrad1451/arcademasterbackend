// CHQ: Claude AI (Sonnet) generated this file
package com.arcade.scoreboard.dto;

import com.arcade.scoreboard.entity.GameType;
import com.arcade.scoreboard.entity.Score;

import java.time.LocalDateTime;

public record ScoreResponse(
        Long id,
        String username,
        GameType gameType,
        Long score,
        LocalDateTime updatedAt
) {
    public static ScoreResponse fromEntity(Score score) {
        return new ScoreResponse(
                score.getId(),
                score.getUsername(),
                score.getGameType(),
                score.getScore(),
                score.getUpdatedAt()
        );
    }
}
