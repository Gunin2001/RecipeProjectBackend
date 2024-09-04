package com.example.RecipeProjectBackend.Entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

public class Recipe {

    @ManyToOne()
    @JoinColumn(name = "id")
    private AppUser appUser;

}
