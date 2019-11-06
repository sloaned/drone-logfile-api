package com.dronelogfileapi.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {


    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/skyward?allowPublicKeyRetrieval=true&useSSL=false");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("strongpassword");
        hikariConfig.setPoolName("database");
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.addDataSourceProperty("cachePrepStmts", true);
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", 250);
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        hikariConfig.addDataSourceProperty("useServerPrepStmts", true);
        hikariConfig.addDataSourceProperty("useLocalSessionState", true);
        hikariConfig.addDataSourceProperty("useLocalTransactionState", true);
        hikariConfig.addDataSourceProperty("rewriteBatchedStatements", true);
        hikariConfig.addDataSourceProperty("cacheResultSetMetadata", true);
        hikariConfig.addDataSourceProperty("cacheServerConfiguration", true);
        hikariConfig.addDataSourceProperty("elideSetAutoCommits", true);
        hikariConfig.addDataSourceProperty("maintainTimeStats", false);
        hikariConfig.addDataSourceProperty("connectionTimeout", 1000);

        return new HikariDataSource(hikariConfig);

    }

}
