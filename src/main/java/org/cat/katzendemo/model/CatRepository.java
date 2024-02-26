package org.cat.katzendemo.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface CatRepository extends CrudRepository<Cat, Long> {

    List<Cat> findAll();

    List<Cat> findCatsByBreed(Breed breed);

    List<Cat> findCatByWeightGreaterThanAndWeightIsLessThan(float min, float max);
}
