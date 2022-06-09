package com.javadatabase.connection;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionUtil {

    private static HikariDataSource datasource;
    
    static {
        HikariConfig configuration = new HikariConfig();
        configuration.setDriverClassName("com.mysql.cj.jdbc.Driver");
        configuration.setJdbcUrl("jdbc:mysql://localhost:3306/java_database?setTimezone=Asia/Jakarta");
        configuration.setUsername("alliano-dev");
        configuration.setPassword("");

        configuration.setMaximumPoolSize(30);
        configuration.setMinimumIdle(5);
        configuration.setIdleTimeout(60_000);
        configuration.setMaxLifetime(5 * 60_000);

        datasource = new HikariDataSource(configuration);
    }

    public static HikariDataSource getDataSource(){
        return datasource;
    }
}
