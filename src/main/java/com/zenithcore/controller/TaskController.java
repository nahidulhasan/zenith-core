package com.zenithcore.controller;

import com.zenithcore.dto.TaskDto;
import com.zenithcore.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService service;
    public TaskController(TaskService service) { this.service = service; }

    @GetMapping
    public List<TaskDto> list() { return service.list(); }

    @PostMapping
    public ResponseEntity<TaskDto> create(@RequestBody TaskDto dto) {
        TaskDto created = service.create(dto);
        return ResponseEntity.created(URI.create("/api/tasks/" + created.id)).body(created);
    }

    @GetMapping("/{id}")
    public TaskDto get(@PathVariable Long id) { return service.getById(id); }

    @PutMapping("/{id}")
    public TaskDto update(@PathVariable Long id, @RequestBody TaskDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
