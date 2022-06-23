package recipes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import recipes.entity.Recipe;
import recipes.repositor.RecipeRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeServicesImp implements RecipeServices {

    @Autowired
    private RecipeRepository recipeRepository;

    @Transactional
    @Override
    public Recipe saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public List<Recipe> printAllRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe getRecipeById(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (recipe.isPresent()) {
            return recipe.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Override
    public void deleteRecipeById(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (recipe.isPresent()) {
            recipeRepository.delete(recipe.get());
            return;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }

    @Override
    public List<Recipe> findByCategory(String category) {
        if (category == null || category.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        List<Recipe> list = recipeRepository.findByCategoryIgnoreCaseOrderByDateDesc(category);
        if (list != null) {
            return list;
        }
        return Collections.emptyList();
    }

    @Override
    public List<Recipe> findByName(String name) {
        if (name == null || name.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        List<Recipe> list = recipeRepository.findByNameIgnoreCaseContainsOrderByDateDesc(name);
        if (list != null) {
            return list;
        }
        return Collections.emptyList();
    }

    @Transactional
    @Override
    public Recipe updateRecipe(Long id, Recipe recipe) {
        Optional<Recipe> temp = recipeRepository.findById(id);
        if (temp.isPresent()) {
            Recipe recipe1 = Recipe.builder()
                    .id(id)
                    .name(recipe.getName())
                    .category(recipe.getCategory())
                    .description(recipe.getDescription())
                    .ingredients(recipe.getIngredients())
                    .directions(recipe.getDirections())
                    .build();

            recipeRepository.save(recipe1);
            return recipe;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }


}
