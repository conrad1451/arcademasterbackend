// CHQ: Claude AI (Sonnet) generated this file
package com.arcade.scoreboard.service;

import com.arcade.scoreboard.dto.ScoreRequest;
import com.arcade.scoreboard.entity.GameType;
import com.arcade.scoreboard.entity.Score;
import com.arcade.scoreboard.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScoreService {

    private final ScoreRepository scoreRepository;

    /**
     * Saves the current score for a user/game pair. If a score already
     * exists for that (username, gameType), it is overwritten - this
     * table always reflects the *current* score, not a history.
     */
    @Transactional
    public Score saveCurrentScore(ScoreRequest request) {
        Score score = scoreRepository
                .findByUsernameAndGameType(request.username(), request.gameType())
                .orElseGet(() -> Score.builder()
                        .username(request.username())
                        .gameType(request.gameType())
                        .build());

        score.setScore(request.score());
        return scoreRepository.save(score);
    }

    @Transactional(readOnly = true)
    public List<Score> getScoresForUser(String username) {
        return scoreRepository.findByUsernameOrderByGameTypeAsc(username);
    }

    @Transactional(readOnly = true)
    public List<Score> getLeaderboardForGame(GameType gameType) {
        return scoreRepository.findByGameTypeOrderByScoreDesc(gameType);
    }
}
