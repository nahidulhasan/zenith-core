package com.zenithcore.service;

import com.zenithcore.dto.TaskDto;
import com.zenithcore.entity.Task;
import com.zenithcore.exception.ResourceNotFoundException;
import com.zenithcore.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) { this.repository = repository; }

    private TaskDto toDto(Task t) {
        return new TaskDto(t.getId(), t.getTitle(), t.getDetails(), t.isCompleted(), t.getCreatedAt());
    }

    public TaskDto create(TaskDto dto) {
        Task t = new Task(dto.title, dto.details);
        t.setCompleted(dto.completed);
        Task saved = repository.save(t);
        return toDto(saved);
    }

    public TaskDto getById(Long id) {
        Task t = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found: " + id));
        return toDto(t);
    }

    public List<TaskDto> list() {
        return repository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public TaskDto update(Long id, TaskDto dto) {
        Task t = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found: " + id));
        if (dto.title != null) t.setTitle(dto.title);
        if (dto.details != null) t.setDetails(dto.details);
        t.setCompleted(dto.completed);
        Task saved = repository.save(t);
        return toDto(saved);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("Task not found: " + id);
        repository.deleteById(id);
    }
}
