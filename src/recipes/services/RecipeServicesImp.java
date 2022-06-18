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
    public Recipe getRecipeById(Long id) {
        if (RecipeRepository.data.get(id) != null) {
            return RecipeRepository.data.get(id);
        }
        return null;
    }

    @Override
    public Recipe saveRecipe(Recipe recipe) {
        Long id = RecipeRepository.data.size() + 1L;
        Recipe temp = Recipe.builder()
                .id(id)
                .name(recipe.getName())
                .description(recipe.getDescription())
                .ingredients(recipe.getIngredients())
                .directions(recipe.getDirections())
                .build();
        RecipeRepository.data.put(temp.getId(), temp);
        return temp;

    }
}
