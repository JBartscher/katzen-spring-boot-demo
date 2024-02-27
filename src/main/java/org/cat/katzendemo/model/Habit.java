package org.cat.katzendemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "Habit")
@Entity
public class Habit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="cat_id", nullable=true)
    private Cat cat;

    public Habit(String name) {
        this.name = name;
    }
}
