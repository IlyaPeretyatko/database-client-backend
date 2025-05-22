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

@Tag(name = "SQL Executor", description = "Ручное выполнение SQL-запросов")
@RestController
@RequiredArgsConstructor
@RequestMapping("/sql")
public class SqlController {

    private final SqlService sqlService;

    @PostMapping(
            value = "/select",
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    @Operation(summary = "Выполнить SELECT-запрос")
    public ResponseEntity<String> executeSelectQuery(@RequestBody String query) {
        if (!query.toUpperCase().startsWith("SELECT ")) {
            return ResponseEntity.badRequest().body("Ошибка: разрешены только SELECT-запросы");
        }
        try {
            String result = sqlService.executeSelectQuery(query);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Некорректный запрос: " + query);
        }
    }

    @PostMapping(
            value = "/insert",
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    @Operation(summary = "Выполнить INSERT-запрос")
    public ResponseEntity<String> executeInsertQuery(@RequestBody String query) {
        if (!query.toUpperCase().startsWith("INSERT ")) {
            return ResponseEntity.badRequest().body("Ошибка: разрешены только INSERT-запросы");
        }
        try {
            int rowsAffected = sqlService.executeUpdateQuery(query);
            return ResponseEntity.ok("Добавлено строк: " + rowsAffected);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Некорректный запрос: " + query);
        }
    }

    @PostMapping(
            value = "/update",
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    @Operation(summary = "Выполнить UPDATE-запрос")
    public ResponseEntity<String> executeUpdateQuery(@RequestBody String query) {
        if (!query.toUpperCase().startsWith("UPDATE ")) {
            return ResponseEntity.badRequest().body("Ошибка: разрешены только UPDATE-запросы");
        }
        try {
            int rowsAffected = sqlService.executeUpdateQuery(query);
            return ResponseEntity.ok("Обновлено строк: " + rowsAffected);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Некорректный запрос: " + query);
        }
    }

    @PostMapping(
            value = "/delete",
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    @Operation(summary = "Выполнить DELETE-запрос")
    public ResponseEntity<String> executeDeleteQuery(@RequestBody String query) {
        if (!query.toUpperCase().startsWith("DELETE ")) {
            return ResponseEntity.badRequest().body("Ошибка: разрешены только DELETE-запросы");
        }
        try {
            int rowsAffected = sqlService.executeUpdateQuery(query);
            return ResponseEntity.ok("Удалено строк: " + rowsAffected);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Некорректный запрос: " + query);
        }
    }
}