package recipes.services;

import recipes.entity.Recipe;

public interface RecipeServices {


    public Recipe printAllRecipe();

    void saveRecipe(Recipe recipe);

}
