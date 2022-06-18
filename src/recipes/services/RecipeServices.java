package recipes.services;

import recipes.entity.Recipe;

public interface RecipeServices {


    public Recipe printAllRecipe();

    public Recipe getRecipeById(Long id);

    Recipe saveRecipe(Recipe recipe);

}
