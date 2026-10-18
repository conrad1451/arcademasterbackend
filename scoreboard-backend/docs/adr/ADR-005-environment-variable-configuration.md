# 5. Environment-variable configuration with local defaults

- Status: Accepted
- Date: 2026-08-04 (commit `e8285d7`), amended 2026-08-07

## Context
The app deploys to Render, where DB credentials are injected as environment variables, but must still run locally with no setup. Credentials were originally hardcoded in `application.properties`.

## Decision
Use Spring placeholder syntax with local defaults in `application.properties`:

| Variable | Default |
|----------|---------|
| `DB_URL` | `jdbc:mysql://localhost:3306/scoreboard?...` |
| `DB_USERNAME` | `root` |
| `DB_PASSWORD` | `changeme` |
| `ALLOWED_ORIGINS` | `http://localhost:3000,http://localhost:5173` (ADR 0008) |

The username variable was first `DB_USER`, but Render and the diagnostic logging used `DB_USERNAME`; the mismatch silently fell back to `root`, so it was renamed to `DB_USERNAME` (2026-08-07). A temporary diagnostic that printed `DB_URL`/`DB_USERNAME` at startup was added during that debugging.

## Consequences
- No secrets in the repo; `.env` is gitignored.
- Silent fallback to defaults can mask misconfiguration (exactly what happened with `DB_USER`). Consider failing fast in production profiles.
- The default password `changeme` should never reach production.
- Follow-up: confirm the diagnostic `System.out.println` calls in `ScoreboardApplication` were removed; the commit marked them temporary and they log environment values.
