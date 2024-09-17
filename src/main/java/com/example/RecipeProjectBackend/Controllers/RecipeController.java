package com.example.RecipeProjectBackend.Controllers;

import com.example.RecipeProjectBackend.Entity.Recipe;
import com.example.RecipeProjectBackend.Service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/recipes")
@AllArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @PostMapping("/add")
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe, @RequestParam Long userId) {
        System.out.println(recipe);
        Recipe savedRecipe = recipeService.saveRecipeForUser(recipe, userId);
        return ResponseEntity.ok(savedRecipe);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recipe>> getRecipesByUser(@PathVariable Long userId) {
        List<Recipe> recipes = recipeService.getAllRecipesByUser(userId);
        return ResponseEntity.ok(recipes);
    }
}
