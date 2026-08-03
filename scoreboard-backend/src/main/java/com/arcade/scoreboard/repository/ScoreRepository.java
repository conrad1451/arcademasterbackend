package com.arcade.scoreboard.repository;

import com.arcade.scoreboard.entity.GameType;
import com.arcade.scoreboard.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScoreRepository extends JpaRepository<Score, Long> {

    Optional<Score> findByUsernameAndGameType(String username, GameType gameType);

    List<Score> findByUsernameOrderByGameTypeAsc(String username);

    List<Score> findByGameTypeOrderByScoreDesc(GameType gameType);
}
