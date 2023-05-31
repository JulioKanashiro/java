package com.br.api.banco.jdbc.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jukan
 */

public class SlackController {

    private JdbcTemplate jdbcTemplate;

    public SlackController() {
        // Configurar o DataSource
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl("jdbc:sqlserver://analayx.database.windows.net:1433;database=analyx;user=analyx-admin@analayx;password=#Gfgrupo2;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
        dataSource.setUsername("analyx-admin");
        dataSource.setPassword("#Gfgrupo2");

        // Criar o JdbcTemplate com o DataSource configurado
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public String fetchSlackWebhook() {
        String query = "select urlSlack as url from empresa where id = 3";

        return jdbcTemplate.queryForObject(query, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("url");
            }
        });
    }

    public String getSlackWebhook(){
        SlackController webhookFetcher = new SlackController();
        String slackWebhook = webhookFetcher.fetchSlackWebhook();
        
        return slackWebhook;
    }
}
