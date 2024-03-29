package org.cat.katzendemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "Cat")
@Entity
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String name;
    private Breed breed;
    private Float weight;

    @OneToMany(mappedBy = "cat")
    private Set<Habit> habits;

    public Cat(String name, Breed breed, Float weight) {
        this.name = name;
        this.breed = breed;
        this.weight = weight;
    }
}
