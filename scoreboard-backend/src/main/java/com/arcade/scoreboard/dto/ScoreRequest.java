// CHQ: Claude AI (Sonnet) generated this file
package com.arcade.scoreboard.dto;

import com.arcade.scoreboard.entity.GameType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ScoreRequest(

        @NotBlank(message = "username must not be blank")
        String username,

        @NotNull(message = "gameType is required (GAME_2048, PING_PONG, or TETRIS)")
        GameType gameType,

        @NotNull(message = "score is required")
        @Min(value = 0, message = "score must be zero or greater")
        Long score
) {
}
