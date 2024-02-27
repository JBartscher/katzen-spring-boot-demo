package org.cat.katzendemo.service;

import org.cat.katzendemo.model.Breed;
import org.cat.katzendemo.model.Cat;
import org.cat.katzendemo.model.CatRepository;
import org.cat.katzendemo.model.dto.CatDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CatService {
    private final CatRepository catRepository;

    public CatService(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public Page<Cat> getAll(Pageable pageable) {
        return catRepository.findAll(pageable);
    }

    public Page<Cat> getByBreed(Breed breed, Pageable pageable) {
        return catRepository.findCatsByBreed(breed, pageable);
    }

    public Cat getById(Long id) throws NoSuchElementException {
        Optional<Cat> cat = catRepository.findCatById(id);
        return cat.orElseThrow();
    }

    public Cat create(CatDTO catDTO) {
        Cat newCat = new Cat(catDTO.name(), catDTO.breed(), catDTO.weight());
        return catRepository.save(newCat);
    }

}
