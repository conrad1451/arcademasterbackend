// CHQ: Claude AI (Sonnet) generated this file
package com.arcade.scoreboard.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Stores the current (most recent) score for a given username + game.
 * There is exactly one row per (username, gameType) pair - saving a new
 * score for the same user/game overwrites the previous one.
 */
@Entity
@Table(
        name = "scores",
        uniqueConstraints = @UniqueConstraint(
                name = "uq_username_game",
                columnNames = {"username", "game_type"}
        )
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(name = "game_type", nullable = false, length = 30)
    private GameType gameType;

    @Column(nullable = false)
    private Long score;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    @PreUpdate
    private void touchUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }
}
