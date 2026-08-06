// CHQ: Claude AI (Sonnet) generated this file
package com.arcade.scoreboard.controller;

import com.arcade.scoreboard.dto.ScoreRequest;
import com.arcade.scoreboard.dto.ScoreResponse;
import com.arcade.scoreboard.entity.GameType;
import com.arcade.scoreboard.entity.Score;
import com.arcade.scoreboard.service.ScoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scores")
@RequiredArgsConstructor
public class ScoreController {

    private final ScoreService scoreService;

    /**
     * Called by the frontend when a game ends. Saves (or overwrites) the
     * user's current score for that game.
     */
    @PostMapping
    public ResponseEntity<ScoreResponse> submitScore(@Valid @RequestBody ScoreRequest request) {
        Score saved = scoreService.saveCurrentScore(request);
        // return ResponseEntity.status(HttpStatus.OK).body(ScoreResponse.fromEntity(saved));
        return ResponseEntity.status(HttpStatus.CREATED).body(ScoreResponse.fromEntity(saved));
    }

    /** All of a single user's current scores, one per game. */
    @GetMapping("/user/{username}")
    public ResponseEntity<List<ScoreResponse>> getScoresForUser(@PathVariable String username) {
        List<ScoreResponse> scores = scoreService.getScoresForUser(username)
                .stream()
                .map(ScoreResponse::fromEntity)
                .toList();
        return ResponseEntity.ok(scores);
    }

    /** Leaderboard for a single game, highest score first. */
    @GetMapping("/game/{gameType}")
    public ResponseEntity<List<ScoreResponse>> getLeaderboardForGame(@PathVariable GameType gameType) {
        List<ScoreResponse> scores = scoreService.getLeaderboardForGame(gameType)
                .stream()
                .map(ScoreResponse::fromEntity)
                .toList();
        return ResponseEntity.ok(scores);
    }
}
