package com.example.RecipeProjectBackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.RecipeProjectBackend.Entity.Recipe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    // Custom query to find recipes by user ID
    @Query("SELECT r FROM Recipe r JOIN r.users u WHERE u.id = :userId")
    List<Recipe> findAllByUserId(@Param("userId") Long userId);
}
