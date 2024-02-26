package org.cat.katzendemo.service;

import org.cat.katzendemo.model.Breed;
import org.cat.katzendemo.model.Cat;
import org.cat.katzendemo.model.CatRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatService {
    private final CatRepository catRepository;

    public CatService(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public List<Cat> getAll(){
        return catRepository.findAll();
    }

    public List<Cat> getByBreed(Breed breed){
        return catRepository.findCatsByBreed(breed);
    }

    public Cat getById(Long id) throws ChangeSetPersister.NotFoundException {
        Optional<Cat> cat = catRepository.findById(id);
        return cat.orElseThrow(ChangeSetPersister.NotFoundException::new);
    }
}
