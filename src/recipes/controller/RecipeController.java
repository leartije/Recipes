package recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import recipes.entity.Recipe;
import recipes.services.RecipeServices;

@RestController
public class RecipeController {

    @Autowired
    private RecipeServices recipeServices;

    @GetMapping(path = "/api/recipe")
    public Recipe printAllRecipe() {
        return recipeServices.printAllRecipe();
    }

    @PostMapping(path = "/api/recipe")
    public void saveRecipe(@RequestBody Recipe recipe) {
        recipeServices.saveRecipe(recipe);
    }


}
