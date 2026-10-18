# 7. Disable Hibernate JDBC metadata access; set dialect explicitly

- Status: Accepted
- Date: 2026-08-04 (commits `ee6b308`, `b9e1334`, `3b1dcda`)

## Context
Startup/build on Render failed because Hibernate tried to connect to the database to detect metadata in an environment where the DB wasn't reachable during the build phase. Removing the explicit `MySQLDialect` (to silence the HHH90000025 deprecation warning) made things worse, since without metadata Hibernate cannot infer the dialect.

## Decision
Set:
- `spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults=false`
- `spring.jpa.properties.hibernate.boot.allow_jdbc_metadata_access=false`
- `spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect` (restored)

## Consequences
- App boots without a live DB connection at initialization.
- The dialect is now our responsibility; changing database vendors requires updating it.
- The HHH90000025 deprecation warning returns.
- Hibernate may not detect DB version-specific features automatically.
