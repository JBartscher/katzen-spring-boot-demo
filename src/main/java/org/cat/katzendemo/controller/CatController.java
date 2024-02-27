package org.cat.katzendemo.controller;

import jakarta.websocket.server.PathParam;
import org.cat.katzendemo.model.Breed;
import org.cat.katzendemo.model.Cat;
import org.cat.katzendemo.model.dto.CatDTO;
import org.cat.katzendemo.service.CatService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.text.MessageFormat;
import java.util.NoSuchElementException;


@RestController()
@RequestMapping(value = "/cat", produces = "application/json", consumes = "application/json")
public class CatController {

    private static final Logger LOG = LoggerFactory.getLogger(CatController.class);

    private final CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cat> getById(@PathVariable("id") Long catId) throws NoSuchElementException {
        LOG.info(MessageFormat.format("querying for cat with id {0}", catId));
        Cat cat = catService.getById(catId);
        return ResponseEntity.ok(cat);
    }

    @GetMapping
    public ResponseEntity<Page<Cat>> getCats(@PageableDefault(value = 10, page = 0) Pageable pageable) throws NoSuchElementException {
        LOG.info(MessageFormat.format("querying for cats with page {0} of size {1}", pageable.getPageNumber(), pageable.getPageSize()));
        Page<Cat> cats = catService.getAll(pageable);
        return ResponseEntity.ok(cats);
    }

    @GetMapping("/breed/{breed}")
    public ResponseEntity<Page<Cat>> getCatsByBreed(@PathVariable("breed") Breed breed, @PageableDefault(value = 10, page = 0) Pageable pageable) throws NoSuchElementException {
        LOG.info(MessageFormat.format("querying for cats with breed {0}", breed));
        Page<Cat> cats = catService.getByBreed(breed, pageable);
        return ResponseEntity.ok(cats);
    }

    @PostMapping
    public ResponseEntity<Cat> createCat(@RequestBody CatDTO catDTO) {
        var newCat = catService.create(catDTO);
        return ResponseEntity.created(URI.create("/cat/" + newCat.getId())).body(newCat);
    }


}
