# 8. Configurable CORS origins via `ALLOWED_ORIGINS`

- Status: Accepted (supersedes the hardcoded list)
- Date: 2026-08-04 (hardcoded), 2026-10-13 (configurable, commit `9ad851b`)

## Context
The browser frontend is on a different origin (`arcademaster.vercel.app` in production, Vite/CRA ports locally). Origins were hardcoded in `CorsConfig`, and each new dev port (5174–5176) required a code change and redeploy.

## Decision
`CorsConfig` reads a comma-separated `ALLOWED_ORIGINS` property via `@Value`, defaulting to `http://localhost:3000,http://localhost:5173`. CORS applies to `/api/**` and allows `GET, POST, PUT, DELETE, OPTIONS` with any headers.

## Consequences
- Origins change per environment without a rebuild.
- Production must set `ALLOWED_ORIGINS` explicitly; if unset, the production frontend will be blocked.
- Allowed methods include PUT/DELETE although no such endpoints exist; harmless but broader than needed.
