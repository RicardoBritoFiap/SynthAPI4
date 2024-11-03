package com.fiap.synthia;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;

@Configuration
public class FlywayMigrationConfig {

    @Bean
    public FlywayMigrationStrategy flywayMigrationStrategy() {
        return flyway -> {
            Flyway.configure()
                  .baselineOnMigrate(true)
                  .dataSource(flyway.getConfiguration().getDataSource())
                  .load()
                  .migrate();
        };
    }
}