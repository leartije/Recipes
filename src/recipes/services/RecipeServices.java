package recipes.services;

import recipes.entity.Recipe;

import java.util.List;

public interface RecipeServices {

    List<Recipe> printAllRecipes();

    Recipe getRecipeById(Long id);

    Recipe saveRecipe(Recipe recipe);

    void deleteRecipeById(Long id);
    List<Recipe> findByCategory(String category);

    List<Recipe> findByName(String name);

    Recipe updateRecipe(Long id, Recipe recipe);



}
