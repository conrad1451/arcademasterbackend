// scoreboard-backend/src/test/java/com/arcade/scoreboard/controller/ScoreControllerTest.java

package com.arcade.scoreboard.controller;

import com.arcade.scoreboard.dto.ScoreRequest;
import com.arcade.scoreboard.entity.GameType;
import com.arcade.scoreboard.entity.Score;
import com.arcade.scoreboard.service.ScoreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ScoreController.class)
class ScoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ScoreService scoreService;

    @Test
    @DisplayName("POST /api/scores should successfully submit a score and return 201 Created")
    void submitScore_Success() throws Exception {
        ScoreRequest request = new ScoreRequest("gamer123", GameType.TETRIS, 300L);
        Score savedScore = Score.builder()
                .id(1L)
                .username("gamer123")
                .gameType(GameType.TETRIS) // CHQ: Gemini AI fixed
                .score(300L)
                .build();

        when(scoreService.saveCurrentScore(any(ScoreRequest.class))).thenReturn(savedScore);

        mockMvc.perform(post("/api/scores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("gamer123"))
                .andExpect(jsonPath("$.gameType").value("TETRIS"))
                .andExpect(jsonPath("$.score").value(300L));
    }

    @Test
    @DisplayName("GET /api/scores/user/{username} should return user scores list")
    void getScoresForUser_Success() throws Exception {
        Score score = Score.builder()
                .id(1L)
                .username("gamer123")
                .gameType(GameType.TETRIS) // CHQ: Gemini AI fixed
                .score(300L)
                .build();

        when(scoreService.getScoresForUser("gamer123")).thenReturn(List.of(score));

        mockMvc.perform(get("/api/scores/user/gamer123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("gamer123"))
                .andExpect(jsonPath("$[0].score").value(300L));
    }

    @Test
    @DisplayName("GET /api/scores/game/{gameType} should return leaderboard list")
    void getLeaderboardForGame_Success() throws Exception {
        Score score = Score.builder()
                .id(1L)
                .username("gamer123")
                .gameType(GameType.TETRIS)  // CHQ: Gemini AI fixed
                .score(500L)
                .build();

        when(scoreService.getLeaderboardForGame(GameType.TETRIS)).thenReturn(List.of(score));  // CHQ: Gemini AI fixed

        mockMvc.perform(get("/api/scores/game/TETRIS"))  // CHQ: Gemini AI fixed
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("gamer123"))
                .andExpect(jsonPath("$[0].score").value(500));
    }
}