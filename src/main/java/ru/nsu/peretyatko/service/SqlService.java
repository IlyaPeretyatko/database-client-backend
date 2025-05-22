// SqlService.java
package ru.nsu.peretyatko.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SqlService {

    private final JdbcTemplate jdbcTemplate;

    public SqlService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String executeSelectQuery(String query) throws Exception {
        List<Map<String, Object>> result = jdbcTemplate.queryForList(query);
        return result.toString();
    }

    public int executeUpdateQuery(String query) throws Exception {
        return jdbcTemplate.update(query);
    }
}