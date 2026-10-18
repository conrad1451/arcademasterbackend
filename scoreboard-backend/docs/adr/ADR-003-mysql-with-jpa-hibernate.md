# 3. MySQL with JPA/Hibernate and `ddl-auto=update`

- Status: Accepted
- Date: 2026-08-02 (commit `2bb9525`)

## Context
Scores are simple relational data (user, game, score, timestamp). The initial setup targets a local MySQL, with `createDatabaseIfNotExist=true` in the JDBC URL.

## Decision
Use MySQL via Spring Data JPA / Hibernate (`com.mysql.cj.jdbc.Driver`). Let Hibernate manage the schema with `spring.jpa.hibernate.ddl-auto=update`; no migration tool.

## Consequences
- Zero-setup schema creation on first run.
- The README itself notes `update` is convenient for development but not ideal for production: no versioned migrations, and column changes (e.g. the `score` type) are not reliably applied. Adopt Flyway/Liquibase if the schema starts evolving.
- Hibernate's dialect/metadata behavior needed tuning for Render (ADR 0007).
