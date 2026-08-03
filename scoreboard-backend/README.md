# Scoreboard Backend

Spring Boot + MySQL backend that stores each user's **current score** for
each game (2048, Ping Pong, Tetris). Saving a new score for a
user/game overwrites the previous one — this is a "current score" table,
not a full history log.

## Requirements
- Java 17+
- Maven 3.8+
- MySQL 8+ running locally (or update `application.properties` to point elsewhere)

## Setup

1. Make sure MySQL is running and update the credentials in
   `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/scoreboard?useSSL=false&serverTimezone=UTC&createDatabaseIfNotExist=true
   spring.datasource.username=root
   spring.datasource.password=changeme
   ```
   The `createDatabaseIfNotExist=true` flag means you don't need to manually
   create the `scoreboard` database — Hibernate/MySQL will create the
   `scores` table on first run (`ddl-auto=update`).

2. Run it:
   ```bash
   mvn spring-boot:run
   ```
   The API will be available at `http://localhost:8080`.

## Endpoints

### Submit a score (call this when a game ends)
```
POST /api/scores
Content-Type: application/json

{
  "username": "alice",
  "gameType": "TETRIS",
  "score": 4200
}
```
`gameType` must be one of: `GAME_2048`, `PING_PONG`, `TETRIS`.

Response `200 OK`:
```json
{
  "id": 1,
  "username": "alice",
  "gameType": "TETRIS",
  "score": 4200,
  "updatedAt": "2026-08-02T10:15:30"
}
```

### Get a user's current scores across all games
```
GET /api/scores/user/{username}
```

### Get the leaderboard for one game (highest score first)
```
GET /api/scores/game/{gameType}
```
e.g. `GET /api/scores/game/TETRIS`

## Frontend integration example

In each game component, call this when the game transitions to game-over.
For example, in `Tetris.tsx`'s `lockPiece`:

```ts
async function submitScore(username: string, gameType: "GAME_2048" | "PING_PONG" | "TETRIS", score: number) {
  await fetch("http://localhost:8080/api/scores", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ username, gameType, score }),
  });
}

// e.g. inside lockPiece, once setGameOver(true) fires:
submitScore(currentUsername, "TETRIS", score);
```

You'll need a way to know the current username in the frontend (e.g. a
simple text input at the top of the arcade, stored in React state or
localStorage) since there's no login system.

## Notes / things to revisit before production
- CORS is currently locked to `localhost:3000` and `localhost:5173`
  (see `CorsConfig.java`) — add your deployed frontend origin there.
- `spring.jpa.hibernate.ddl-auto=update` is convenient for development but
  not recommended for production; consider Flyway or Liquibase migrations
  instead.
- There's no authentication — anyone who knows a username can overwrite
  that user's score. Fine for a casual/demo arcade, not for anything
  competitive or public-facing without adding real auth later.
