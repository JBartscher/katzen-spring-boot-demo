package org.cat.katzendemo.model.dto;

import org.cat.katzendemo.model.Breed;

public record CatDTO(String name, Breed breed, Float weight) {
}
