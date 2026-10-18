# 10. Testing strategy and Java 25 test support

- Status: Accepted
- Date: 2026-10-16 (commits `d5fdffa`, `477ad62`, `b9ff16a`)

## Context
No tests existed. Core logic (high-score rule, queries) and the REST contract needed coverage. The local JDK is 25, which broke Mockito/Byte Buddy and Lombok processing under the project's defaults.

## Decision
- `ScoreServiceTest`: unit tests with mocked `ScoreRepository` covering new-user save, higher-score update, lower-score no-op, per-user and per-game queries.
- `ScoreControllerTest`: `@WebMvcTest` slice tests for the REST endpoints with a mocked service.
- `pom.xml`: override `lombok.version` to 1.18.38, declare Lombok in `maven-compiler-plugin`'s `annotationProcessorPaths`, and pass `-Dnet.bytebuddy.experimental=true` to Surefire. The Spring Boot plugin's Lombok exclusion was removed.
- `.gitignore` now ignores `*/target/`, `*.log`, and per-module `gitDiffs/`, `gitLogs/`, `buildLogs/`.

## Consequences
- Fast tests, no database needed.
- No repository/integration tests, so the unique constraint, JPA queries and real schema are untested (a Testcontainers MySQL test would cover this).
- The Byte Buddy experimental flag is a workaround for running on a newer JDK than the project targets (17). Prefer aligning `java.version`, CI and Docker on one JDK and dropping the flag later.
