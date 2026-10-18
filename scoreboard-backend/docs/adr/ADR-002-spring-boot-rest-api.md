# 2. Spring Boot REST API on Java 17 / Maven

- Status: Accepted
- Date: 2026-08-02 (commit `2bb9525`)

## Context
The ArcadeMaster frontend (hosted on Vercel) needs somewhere to persist per-user scores for its games (2048, Ping Pong, Tetris). The repo started as a Java backend ("backend written in Java for the arcademaster site").

## Decision
Build a Spring Boot REST service in `scoreboard-backend/` using Maven, with `java.version` 17. Layering: `controller` → `service` → `repository` → `entity`, with `dto` records for requests/responses, plus `config` and `exception` packages. Lombok reduces entity boilerplate.

## Consequences
- Conventional, well-supported stack; easy to test (see ADR 0010).
- Lombok adds annotation-processor configuration that has to be maintained (see ADR 0010).
- `java.version` is 17 while tests have been run on Java 25 (ADR 0010); the two should be kept consistent deliberately.
