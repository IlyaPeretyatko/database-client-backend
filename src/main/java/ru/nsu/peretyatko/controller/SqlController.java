package ru.nsu.peretyatko.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.peretyatko.service.SqlService;

import java.util.List;
import java.util.Map;

@Tag(name = "SQL Executor", description = "Универсальный SQL-исполнитель")
@RestController
@RequiredArgsConstructor
@RequestMapping("/sql")
public class SqlController {

    private final SqlService sqlService;

    @PostMapping(
            value = "/execute",
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Выполнить SQL-запрос")
    public ResponseEntity<?> executeQuery(@RequestBody String query) {
        try {
            Object result = sqlService.executeQuery(query);

            if (result instanceof List<?>) {
                return ResponseEntity.ok((List<Map<String, Object>>) result);
            } else {
                return ResponseEntity.ok(true);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка выполнения запроса: " + e.getMessage());
        }
    }
}