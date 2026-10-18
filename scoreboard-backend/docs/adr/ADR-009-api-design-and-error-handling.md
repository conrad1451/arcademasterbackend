# 9. API design, validation and error handling

- Status: Accepted
- Date: 2026-08-02 – 2026-08-07

## Context
The frontend needs to submit scores, read a player's scores, read a per-game leaderboard, and check server health (useful for Render cold starts).

## Decision
- Endpoints:
  - `POST /api/scores` returns `201 Created` with the saved score (changed from 200 on 2026-08-06)
  - `GET /api/scores/user/{username}`
  - `GET /api/scores/game/{gameType}` (sorted by score descending)
  - `GET /api/health` in a dedicated `HealthController` (health was briefly inside `ScoreController`, which caused path confusion and was split out on 2026-08-07)
- Request/response bodies are Java records (`ScoreRequest`, `ScoreResponse`); entities are not exposed directly.
- Validation with Bean Validation: `username` not blank, `gameType` required, `score` required and `>= 0`.
- `GlobalExceptionHandler` returns JSON 400s for validation failures (with per-field messages) and `IllegalArgumentException`.

## Consequences
- Stable, small contract documented in `scoreboard-backend/README.md`.
- POST returns 201 even when the score is not higher and nothing is created or changed; semantically loose.
- Leaderboard endpoint is unpaginated; it will grow with the user base. Consider a `limit`/top-N.
- A hand-written health endpoint reports static text; it does not check the database. Spring Actuator would give a real readiness check.
