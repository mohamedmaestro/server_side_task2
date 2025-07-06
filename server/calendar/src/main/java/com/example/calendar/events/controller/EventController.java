package com.example.calendar.events.controller;


import com.example.calendar.events.model.Event;
import com.example.calendar.events.service.EventService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @PostMapping
    public Event create(@RequestBody Event e) {
        return service.create(e);
    }

    @GetMapping
    public List<Map<String, Object>> getAll() {
        List<Event> events = service.getAll();
        List<Map<String, Object>> response = new ArrayList<>();

        for (Event e : events) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("id", e.getId());
            map.put("title", e.getTitle());
            map.put("start", e.getStartDateTime());
            map.put("end", e.getStartDateTime());
            map.put("allDay", e.isAllDay());
            map.put("created", e.getCreatedDate());
            map.put("description", e.getDuration());

            Duration duration = e.getDuration();
            long hours = duration.toHours();
            long minutes = duration.toMinutes() % 60;
            map.put("duration", hours + " hrs " + minutes + " mins");

            response.add(map);
        }

        
       response.removeIf(event -> event.get("start") == null);

response.sort(Comparator.comparing(e -> (LocalDateTime) e.get("start")));


        return response;
    }

    @PutMapping("/{id}")
    public Event update(@PathVariable Long id, @RequestBody Event e) {
        e.setId(id);
        return service.update(e);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/day")
    public List<Event> getByDay(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(LocalTime.MAX);
        return service.getEventsBetween(start, end);
    }

    @GetMapping("/week")
    public List<Event> getByWeek(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        LocalDate startOfWeek = date.with(java.time.DayOfWeek.MONDAY);
        LocalDate endOfWeek = startOfWeek.plusDays(6);
        return service.getEventsBetween(startOfWeek.atStartOfDay(), endOfWeek.atTime(LocalTime.MAX));
    }

    @GetMapping("/month")
    public List<Event> getByMonth(@RequestParam int year, @RequestParam int month) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        return service.getEventsBetween(start.atStartOfDay(), end.atTime(LocalTime.MAX));
    }
}
