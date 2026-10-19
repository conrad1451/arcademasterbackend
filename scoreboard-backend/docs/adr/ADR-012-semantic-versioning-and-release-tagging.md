# 12. Semantic versioning and release tagging

- Status: Accepted
- Date: 2026-10-18

## Context
The repo has no tags or releases, and `scoreboard-backend/pom.xml` declares `<version>1.0.0</version>` from the first commit, even though the service has no authentication, no CI, no schema migrations, and has shipped fixes for a first-submission NPE and a misnamed env var. Frontend, Render deploys and the database all depend on API behavior (paths, status codes, env var names), so changes need to be identifiable and revertable.

## Decision
- Use [Semantic Versioning 2.0.0](https://semver.org): `MAJOR.MINOR.PATCH`, tagged `vX.Y.Z` on `main` as annotated tags.
- Stay on `0.y.z` until the 1.0.0 criteria below are met. While below 1.0, a breaking change bumps MINOR.
- What counts as the public contract: HTTP paths, request/response JSON, status codes, environment variable names, and the DB schema.
- Bump rules, driven by the existing Conventional Commit prefixes:
  - `fix:` → PATCH
  - `feat:` → MINOR
  - `BREAKING CHANGE:` footer or `!` (e.g. `feat!:`) → MINOR before 1.0, MAJOR after
  - `docs:`, `test:`, `chore:`, `build:`, `refactor:` alone do not require a release
- `pom.xml` version must equal the tag without the `v`. Use `-SNAPSHOT` between releases if desired.
- Each release gets a GitHub Release with notes from `CHANGELOG.md` (Keep a Changelog format).
- The Docker image and Render deploy should be traceable to a tag.
- Existing history is tagged retroactively from `scripts/tag-history.sh` (v0.1.0 – v0.6.1).

### 1.0.0 criteria
- Decision recorded on username spoofing / authentication
- Production config fails fast on missing settings
- Concurrency-safe score submission
- CI running tests on every PR
- Versioned schema migrations in place
- Diagnostic logging removed

## Consequences
- Clients and operators can tell what changed and roll back to a known version.
- `pom.xml` should be set back to the current 0.x version to avoid claiming 1.0.0 prematurely.
- Small ongoing release overhead; automatable later with release-please or similar.
- Retroactive tags are approximate milestones chosen from history, not releases that were actually deployed at those points.