package recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import recipes.entity.Recipe;
import recipes.services.RecipeServices;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class RecipeController {

    @Autowired
    private RecipeServices recipeServices;

    @GetMapping("/api/recipe")
    public List<Recipe> printAllRecipes() {
        return recipeServices.printAllRecipes();
    }

    @GetMapping(path = "/api/recipe/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Recipe printAllRecipe(@PathVariable("id") Long id) {
        return recipeServices.getRecipeById(id);
    }

    @PostMapping(path = "/api/recipe/new")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Long> saveRecipe(@RequestBody @Valid Recipe recipe) {
        Recipe recipe1 = recipeServices.saveRecipe(recipe);
        return Map.of("id", recipe1.getId());
    }

    @DeleteMapping(path = "/api/recipe/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecipe(@PathVariable("id") Long id) {
        recipeServices.deleteRecipeById(id);
    }

    @GetMapping(path = "/api/recipe/search/", params = {"category"})
    @ResponseStatus(HttpStatus.OK)
    public List<Recipe> findByCategory(@RequestParam(required = false) String category) {
        return recipeServices.findByCategory(category);
    }

    @GetMapping(path = "/api/recipe/search/", params = {"name"})
    @ResponseStatus(HttpStatus.OK)
    public List<Recipe> findByName(@RequestParam(required = false) String name) {
        return recipeServices.findByName(name);
    }

    @PutMapping(path = "/api/recipe/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Recipe updateRecipe(@PathVariable("id") Long id, @RequestBody @Valid Recipe recipe) {
        return recipeServices.updateRecipe(id, recipe);
    }

}
