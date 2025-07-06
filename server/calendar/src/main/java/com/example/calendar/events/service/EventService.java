package com.example.calendar.events.service;

import com.example.calendar.events.model.Event;
import com.example.calendar.events.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {

    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public Event create(Event e) {
        e.setCreatedDate(LocalDateTime.now());
        return repository.save(e);
    }

    public List<Event> getAll() {
        return repository.findAll();
    }

    public List<Event> getEventsBetween(LocalDateTime start, LocalDateTime end) {
        return repository.findByStartDateTimeBetween(start, end);
    }

    public Event update(Event e) {
        return repository.save(e);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}