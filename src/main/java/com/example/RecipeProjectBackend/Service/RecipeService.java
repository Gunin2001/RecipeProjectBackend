package com.example.RecipeProjectBackend.Service;

import com.example.RecipeProjectBackend.Entity.AppUser;
import com.example.RecipeProjectBackend.Entity.Recipe;
import com.example.RecipeProjectBackend.Repository.AppUserRepository;
import com.example.RecipeProjectBackend.Repository.RecipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final AppUserRepository appUserRepository;

    @Transactional
    public Recipe saveRecipeForUser(Recipe recipe, Long userId) {
        // Save the recipe
        Recipe savedRecipe = recipeRepository.save(recipe);

        // Find the user by ID
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        // Associate the saved recipe with the user
        user.getRecipes().add(savedRecipe);
        savedRecipe.getUsers().add(user);

        // Save the user to update the association
        appUserRepository.save(user);

        // Save the recipe to update the association in the Recipe entity as well
        recipeRepository.save(savedRecipe);

        return savedRecipe;
    }

    public List<Recipe> getAllRecipesByUser(Long userId) {
        return recipeRepository.findAllByUserId(userId);
    }
}
