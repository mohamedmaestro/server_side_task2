package com.example.calendar.events.model;

import jakarta.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    private boolean allDay;

    private LocalDateTime createdDate;

    private String description;

    public Event() {}

    public Event(Long id, String title, LocalDateTime startDateTime, LocalDateTime endDateTime, boolean allDay, LocalDateTime createdDate, String description) {
        this.id = id;
        this.title = title;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.allDay = allDay;
        this.createdDate = createdDate;
        this.description = description;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public LocalDateTime getStartDateTime() { return startDateTime; }

    public void setStartDateTime(LocalDateTime startDateTime) { this.startDateTime = startDateTime; }

    public LocalDateTime getEndDateTime() { return endDateTime; }

    public void setEndDateTime(LocalDateTime endDateTime) { this.endDateTime = endDateTime; }

    public boolean isAllDay() { return allDay; }

    public void setAllDay(boolean allDay) { this.allDay = allDay; }

    public LocalDateTime getCreatedDate() { return createdDate; }

    public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

   
    public Duration getDuration() {
        if (startDateTime != null && endDateTime != null) {
            return Duration.between(startDateTime, endDateTime);
        } else {
            return Duration.ZERO;
        }
    }
}
