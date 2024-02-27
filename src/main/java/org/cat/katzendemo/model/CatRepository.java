package org.cat.katzendemo.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface CatRepository extends JpaRepository<Cat, Long> {

    Optional<Cat> findCatById(Long id);

    Page<Cat> findCatsByBreed(Breed breed, Pageable pageable);

    Page<Cat> findCatByWeightGreaterThanAndWeightIsLessThan(float min, float max, Pageable pageable);
}
