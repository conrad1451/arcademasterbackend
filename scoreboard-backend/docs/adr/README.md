# Architecture Decision Records

Decisions for the ArcadeMaster scoreboard backend (`scoreboard-backend/`), reconstructed from the git history (Aug 2 – Oct 16, 2026).

| # | Title | Status |
|---|-------|--------|
| [ADR-001](ADR-001-record-architecture-decisions.md) | Record architecture decisions | Accepted |
| [ADR-002](ADR-002-spring-boot-rest-api.md) | Spring Boot REST API on Java 17 / Maven | Accepted |
| [ADR-003](ADR-003-mysql-with-jpa-hibernate.md) | MySQL with JPA/Hibernate and `ddl-auto=update` | Accepted |
| [ADR-004](ADR-004-one-row-per-user-game-high-score.md) | One row per (username, game), keeping only the high score | Accepted |
| [ADR-005](ADR-005-environment-variable-configuration.md) | Environment-variable configuration with local defaults | Accepted |
| [ADR-006](ADR-006-docker-multi-stage-build-for-render.md) | Multi-stage Docker build for Render | Accepted |
| [ADR-007](ADR-007-disable-hibernate-jdbc-metadata-access.md) | Disable Hibernate JDBC metadata access; set dialect explicitly | Accepted |
| [ADR-008](ADR-008-configurable-cors-origins.md) | Configurable CORS origins via `ALLOWED_ORIGINS` | Accepted |
| [ADR-009](ADR-009-api-design-and-error-handling.md) | API design, validation and error handling | Accepted |
| [ADR-010](ADR-010-testing-strategy-and-java-25-support.md) | Testing strategy and Java 25 test support | Accepted |
| [ADR-011](ADR-011-ai-assistance-attribution.md) | Attribute AI-assisted code with `CHQ:` comments | Accepted |

> These were written after the fact. "Context" and "Consequences" are inferred from commit messages and diffs; correct anything that doesn't match your actual reasoning.
