package org.cat.katzendemo.service;

import org.cat.katzendemo.model.*;
import org.cat.katzendemo.model.dto.CatDTO;
import org.cat.katzendemo.model.dto.HabitDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class HabitService {
    private final HabitRepository habitRepository;

    public HabitService(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public Page<Habit> getAll(Pageable pageable) {
        return habitRepository.findAll(pageable);
    }

    public Habit getById(Long id) throws NoSuchElementException {
        Optional<Habit> habit = habitRepository.findById(id);
        return habit.orElseThrow();
    }

    public Habit create(HabitDTO habitDTO) {
        Habit newHabit = new Habit(habitDTO.name());
        return habitRepository.save(newHabit);
    }

}
