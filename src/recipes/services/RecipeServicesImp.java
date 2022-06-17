package recipes.services;

import org.springframework.stereotype.Service;
import recipes.entity.Recipe;
import recipes.repositor.RecipeRepository;


import java.util.List;

@Service
public class RecipeServicesImp implements RecipeServices {


    @Override
    public Recipe printAllRecipe() {
        return RecipeRepository.data.get(0);
    }

    @Override
    public void saveRecipe(Recipe recipe) {
        RecipeRepository.data.add(0, recipe);
    }
}
