# 1. Record architecture decisions

- Status: Accepted
- Date: 2026-10-18

## Context
The repo has several non-obvious choices (Hibernate metadata flags, env var names, one-row-per-score) whose reasons live only in commit messages and `CHQ:` comments.

## Decision
Keep ADRs in `docs/adr/`, one Markdown file per decision, numbered sequentially, using Context / Decision / Consequences. Superseded decisions are marked, not deleted.

## Consequences
- Reasoning survives outside git history.
- Small ongoing cost to write an ADR for significant changes.
