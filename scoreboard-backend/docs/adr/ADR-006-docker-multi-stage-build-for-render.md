# 6. Multi-stage Docker build for Render

- Status: Accepted
- Date: 2026-08-02 (commit `8dca41b`)

## Context
Render deploys the service from a container image.

## Decision
`scoreboard-backend/Dockerfile` uses two stages: build with `maven:3.9-eclipse-temurin-17` (`mvn clean package -DskipTests`), then run on `eclipse-temurin:17-jre-alpine`, copying only the jar. Entrypoint is `java -jar app.jar`, exposing 8080.

## Consequences
- Small runtime image, no build tooling in production.
- Tests are skipped in the image build, so CI must run them separately.
- The Dockerfile comment says Spring reads Render's `$PORT` via `server.port=${PORT:8080}`, but `application.properties` hardcodes `server.port=8080`. This works only if Render is routed to 8080; consider changing to `${PORT:8080}`.
- Base image Java (17) differs from the Java 25 used to run tests locally (ADR 0010).
