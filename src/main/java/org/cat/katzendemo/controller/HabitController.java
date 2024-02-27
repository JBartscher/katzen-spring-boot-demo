package org.cat.katzendemo.controller;

import org.cat.katzendemo.model.Habit;
import org.cat.katzendemo.model.dto.HabitDTO;
import org.cat.katzendemo.service.HabitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.text.MessageFormat;
import java.util.NoSuchElementException;


@RestController()
@RequestMapping(value = "/habit", produces = "application/json", consumes = "application/json")
public class HabitController {

    private static final Logger LOG = LoggerFactory.getLogger(HabitController.class);

    private final HabitService habitService;

    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Habit> getById(@PathVariable("id") Long habitId) throws NoSuchElementException {
        LOG.info(MessageFormat.format("querying for habit with id {0}", habitId));
        Habit habit = habitService.getById(habitId);
        return ResponseEntity.ok(habit);
    }

    @GetMapping
    public ResponseEntity<Page<Habit>> getHabits(@PageableDefault(value = 10, page = 0) Pageable pageable) throws NoSuchElementException {
        LOG.info(MessageFormat.format("querying for habits with page {0} of size {1}", pageable.getPageNumber(), pageable.getPageSize()));
        Page<Habit> habits = habitService.getAll(pageable);
        return ResponseEntity.ok(habits);
    }

    @PostMapping
    public ResponseEntity<Habit> createHabit(@RequestBody HabitDTO habitDTO) {
        var newHabit= habitService.create(habitDTO);
        return ResponseEntity.created(URI.create("/habit/" + newHabit.getId())).body(newHabit);
    }


}
