// scoreboard-backend/src/main/java/com/arcade/scoreboard/controller/ScoreController.java

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
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HealthController {
    /**
     * Called to perform a health check of the server
    */
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> getHealth() {
        return ResponseEntity.ok(Map.of("status", "The server is working as expected"));
    }
}
