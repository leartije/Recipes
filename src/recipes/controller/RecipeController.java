package recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import recipes.entity.Recipe;
import recipes.services.RecipeServices;

import java.util.Map;

@RestController
public class RecipeController {

    @Autowired
    private RecipeServices recipeServices;

    @GetMapping(path = "/api/recipe/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Recipe printAllRecipe(@PathVariable("id") Long id) {
        if (recipeServices.getRecipeById(id) != null) {
            return recipeServices.getRecipeById(id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = "/api/recipe/new")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Long> saveRecipe(@RequestBody Recipe recipe) {
        Recipe recipe1 = recipeServices.saveRecipe(recipe);
        return Map.of("id", recipe1.getId());
    }


}
