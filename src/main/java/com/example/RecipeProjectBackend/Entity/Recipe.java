package com.example.RecipeProjectBackend.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Recipe {

    @Id
    @SequenceGenerator(
            name = "recipe_sequence",
            sequenceName = "recipe_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "recipe_sequence"
    )
    private Long id;

    private String name;
    private String description;

    @ManyToMany(mappedBy = "recipes", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<AppUser> users = new HashSet<>();

    public Recipe(String name, String description) {
        this.name = name;
        this.description = description;
    }
}

