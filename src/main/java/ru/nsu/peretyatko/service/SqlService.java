package ru.nsu.peretyatko.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SqlService {

    private final JdbcTemplate jdbcTemplate;

    public SqlService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Object executeQuery(String query) {
        String upperQuery = query.toUpperCase().trim();

        if (upperQuery.startsWith("SELECT")) {
            return jdbcTemplate.queryForList(query);
        } else {
            return jdbcTemplate.update(query);
        }
    }
}