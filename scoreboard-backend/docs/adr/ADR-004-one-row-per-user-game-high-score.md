# 4. One row per (username, game), keeping only the high score

- Status: Accepted (refines the original "overwrite on every submission" behavior)
- Date: 2026-08-02 (initial), 2026-08-05 (high-score rule), 2026-08-07 (null fix)

## Context
The leaderboard needs each player's current standing per game, not a history. The first version stored the *most recent* score and overwrote it on each submission, which meant a bad run could erase a player's best. Note: there is no authentication; `username` is a client-supplied string.

## Decision
- `Score` has a unique constraint `uq_username_game` on (`username`, `game_type`); `GameType` is stored as a string (`@Enumerated(STRING)`): `GAME_2048`, `PING_PONG`, `TETRIS`.
- `ScoreService.saveCurrentScore` finds or creates the row and only updates it if the submitted score is strictly higher; otherwise it returns the existing row unchanged.
- `Score.score` defaults to `0L` via `@Builder.Default` so a first-ever submission doesn't NPE on unboxing during the comparison.
- `updated_at` is maintained by `@PrePersist`/`@PreUpdate`.
- Queries: `findByUsernameAndGameType`, `findByUsernameOrderByGameTypeAsc`, `findByGameTypeOrderByScoreDesc`.

## Consequences
- Table size is bounded by users × games; leaderboard queries are simple.
- No score history, analytics, or "most recent run" view without a schema change.
- The unique constraint is the last line of defense, but find-then-save is not atomic: two concurrent first submissions from one user could race and one would fail on the constraint.
- Storing enums as strings keeps data readable and safe against reordering, at a small storage cost.
- Since usernames are unauthenticated, anyone can submit scores for any name.
