// CHQ: Claude AI (Sonnet) generated file

package com.arcade.scoreboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScoreboardApplication {

    public static void main(String[] args) {
        // CHQ: Claude AI (Sonnet): TEMPORARY DIAGNOSTIC: confirm Render is actually passing DB_URL through
        // to the container. Remove this once the connection issue is resolved.
        System.out.println("DIAGNOSTIC: DB_URL env var = [" + System.getenv("DB_URL") + "]");
        System.out.println("DIAGNOSTIC: DB_USERNAME env var = [" + System.getenv("DB_USERNAME") + "]");

        SpringApplication.run(ScoreboardApplication.class, args);
    }
}