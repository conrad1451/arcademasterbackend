// scoreboard-backend/src/test/java/com/arcade/scoreboard/service/ScoreServiceTest.java

package com.arcade.scoreboard.service;

import com.arcade.scoreboard.dto.ScoreRequest;
import com.arcade.scoreboard.entity.GameType;
import com.arcade.scoreboard.entity.Score;
import com.arcade.scoreboard.repository.ScoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScoreServiceTest {

    @Mock
    private ScoreRepository scoreRepository;

    @InjectMocks
    private ScoreService scoreService;

    private Score existingScore;

    @BeforeEach
    void setUp() {
        existingScore = Score.builder()
                .id(1L)
                .username("arcade_pro")
                .gameType(GameType.TETRIS)
                .score(100L)
                .build();
    }

    @Test
    @DisplayName("Should create and save new score when none exists for user and game")
    void saveCurrentScore_NewScore() {
        ScoreRequest request = new ScoreRequest("new_player", GameType.TETRIS, 250L);

        when(scoreRepository.findByUsernameAndGameType("new_player", GameType.TETRIS))
                .thenReturn(Optional.empty());
        // CHQ: Gemini AI: Fixed: changed 0L to integer 0
        when(scoreRepository.save(any(Score.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Score result = scoreService.saveCurrentScore(request);

        assertThat(result.getUsername()).isEqualTo("new_player");
        assertThat(result.getScore()).isEqualTo(250L);
        verify(scoreRepository, times(1)).save(any(Score.class));
    }

    @Test
    @DisplayName("Should update score when incoming score is higher than existing score")
    void saveCurrentScore_HigherScoreUpdates() {
        ScoreRequest request = new ScoreRequest("arcade_pro", GameType.TETRIS, 150L);

        when(scoreRepository.findByUsernameAndGameType("arcade_pro", GameType.TETRIS))
                .thenReturn(Optional.of(existingScore));
        when(scoreRepository.save(any(Score.class))).thenReturn(existingScore);

        Score result = scoreService.saveCurrentScore(request);

        assertThat(result.getScore()).isEqualTo(150L);
        verify(scoreRepository, times(1)).save(existingScore);
    }

    @Test
    @DisplayName("Should NOT update score when incoming score is lower or equal to existing score")
    void saveCurrentScore_LowerScoreIgnored() {
        ScoreRequest request = new ScoreRequest("arcade_pro", GameType.TETRIS, 50L);

        when(scoreRepository.findByUsernameAndGameType("arcade_pro", GameType.TETRIS))
                .thenReturn(Optional.of(existingScore));

        Score result = scoreService.saveCurrentScore(request);

        assertThat(result.getScore()).isEqualTo(100L); // Remains unchanged
        verify(scoreRepository, never()).save(any(Score.class));
    }

    @Test
    @DisplayName("Should retrieve all scores for a given user ordered by game type")
    void getScoresForUser() {
        when(scoreRepository.findByUsernameOrderByGameTypeAsc("arcade_pro"))
                .thenReturn(List.of(existingScore));

        List<Score> results = scoreService.getScoresForUser("arcade_pro");

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getUsername()).isEqualTo("arcade_pro");
        verify(scoreRepository, times(1)).findByUsernameOrderByGameTypeAsc("arcade_pro");
    }

    @Test
    @DisplayName("Should retrieve leaderboard for a game sorted by highest score first")
    void getLeaderboardForGame() {
        when(scoreRepository.findByGameTypeOrderByScoreDesc(GameType.TETRIS))
                .thenReturn(List.of(existingScore));

        List<Score> results = scoreService.getLeaderboardForGame(GameType.TETRIS);

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getScore()).isEqualTo(100L);
        verify(scoreRepository, times(1)).findByGameTypeOrderByScoreDesc(GameType.TETRIS);
    }
}