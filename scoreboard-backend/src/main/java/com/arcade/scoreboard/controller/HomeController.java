// scoreboard-backend/src/main/java/com/arcade/scoreboard/controller/HomeController.java

package com.arcade.scoreboard.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// CHQ: Copilot generated file

@RestController
public class HomeController {

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String home() {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <title>ArcadeMaster API</title>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        max-width: 800px;
                        margin: 40px auto;
                        line-height: 1.6;
                        padding: 0 20px;
                    }
                    code {
                        background: #f4f4f4;
                        padding: 2px 6px;
                        border-radius: 4px;
                    }
                </style>
            </head>
            <body>
                <h1>ArcadeMaster Scoreboard API</h1>

                <p>
                    Backend service for storing and retrieving ArcadeMaster
                    leaderboard scores.
                </p>

                <h2>Available Endpoints</h2>
                <ul>
                    <li><code>GET /api/health</code></li>
                    <li><code>POST /api/scores</code></li>
                    <li><code>GET /api/scores/user/{username}</code></li>
                    <li><code>GET /api/scores/game/{gameType}</code></li>
                </ul>

                <h2>Supported Games</h2>
                <ul>
                    <li>GAME_2048</li>
                    <li>PING_PONG</li>
                    <li>TETRIS</li>
                </ul>

                <p>Status: ✅ Online</p>
            </body>
            </html>
            """;
    }
}
