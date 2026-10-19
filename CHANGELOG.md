# Changelog

All notable changes to this project are documented here. Format: [Keep a Changelog](https://keepachangelog.com/en/1.1.0/). Versioning: [SemVer](https://semver.org) (see ADR 0012). Versions below were assigned retroactively.

## [Unreleased]

## [0.6.1] - 2026-10-16
### Added
- Unit tests for `ScoreService` and `@WebMvcTest` tests for `ScoreController`.
### Changed
- Build: Lombok 1.18.38, annotation processor paths, Surefire Byte Buddy experimental flag for Java 25.
- `.gitignore` covers `*/target/`, `*.log`, `*/gitLogs/`.

## [0.6.0] - 2026-10-13
### Changed
- CORS allowed origins come from the `ALLOWED_ORIGINS` env var (comma-separated), defaulting to local dev origins.

## [0.5.0] - 2026-08-07
### Changed
- **Breaking:** health endpoint moved from `/api/scores/health` to `/api/health` (dedicated `HealthController`).
### Fixed
- NPE on a user's first submission for a game (`Score.score` now defaults to 0).
- DB username read from `DB_USERNAME` instead of `DB_USER`.
- Missing `Map` import in `ScoreController`.

## [0.4.0] - 2026-08-06
### Added
- `GET /api/scores/health` endpoint.
### Changed
- **Breaking:** `POST /api/scores` returns `201 Created` instead of `200 OK`.

## [0.3.0] - 2026-08-05
### Changed
- Submissions only update a stored score if the new score is higher (high-score-per-game rule).
### Known issues
- First-ever submission for a user and game throws an NPE (fixed in 0.5.0).

## [0.2.1] - 2026-08-04
### Changed
- CORS allows the production frontend and local ports 5174–5176.

## [0.2.0] - 2026-08-04
### Added
- Environment-variable configuration (`DB_URL`, `DB_USER`, `DB_PASSWORD`) with local defaults.
- Hibernate JDBC metadata access disabled and MySQL dialect set explicitly, for Render builds.

## [0.1.0] - 2026-08-02
### Added
- Spring Boot backend: `POST /api/scores`, `GET /api/scores/user/{username}`, `GET /api/scores/game/{gameType}`, validation, global error handling, CORS.
- Multi-stage Dockerfile.

[Unreleased]: https://github.com/OWNER/REPO/compare/v0.6.1...HEAD
[0.6.1]: https://github.com/OWNER/REPO/compare/v0.6.0...v0.6.1
[0.6.0]: https://github.com/OWNER/REPO/compare/v0.5.0...v0.6.0
[0.5.0]: https://github.com/OWNER/REPO/compare/v0.4.0...v0.5.0
[0.4.0]: https://github.com/OWNER/REPO/compare/v0.3.0...v0.4.0
[0.3.0]: https://github.com/OWNER/REPO/compare/v0.2.1...v0.3.0
[0.2.1]: https://github.com/OWNER/REPO/compare/v0.2.0...v0.2.1
[0.2.0]: https://github.com/OWNER/REPO/compare/v0.1.0...v0.2.0
[0.1.0]: https://github.com/OWNER/REPO/releases/tag/v0.1.0