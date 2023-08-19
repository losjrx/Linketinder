package com.projeto.poo;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection recuperarConexao(){
        try{
            return createDataSource().getConnection();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private HikariDataSource createDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/linketinder");
        config.setUsername("root");
        config.setPassword("admin");
        config.setMaximumPoolSize(10);

        return new HikariDataSource(config);
    }
}
