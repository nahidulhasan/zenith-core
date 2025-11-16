package com.zenithcore.dto;

import java.time.Instant;

public class TaskDto {
    public Long id;
    public String title;
    public String details;
    public boolean completed;
    public Instant createdAt;

    public TaskDto() {}

    public TaskDto(Long id, String title, String details, boolean completed, Instant createdAt) {
        this.id = id;
        this.title = title;
        this.details = details;
        this.completed = completed;
        this.createdAt = createdAt;
    }
}
