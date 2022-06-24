package recipes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import recipes.entity.Recipe;
import recipes.entity.UserDetailsImp;
import recipes.repositor.RecipeRepository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RecipeServicesImp implements RecipeServices {

    @Autowired
    private RecipeRepository recipeRepository;

    @Transactional
    @Override
    public Recipe saveRecipe(Recipe recipe) {
        Object currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (currentUser instanceof UserDetails) {
            if (recipe != null) {
                recipe.setUser(((UserDetailsImp) currentUser).getUser());
                return recipeRepository.save(recipe);
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
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
        if (recipe.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Object currentLogInUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!Objects.equals(recipe.get().getUser().getId(), ((UserDetailsImp) currentLogInUser).getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        recipeRepository.deleteById(id);
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
    public Recipe updateRecipe(Long id, Recipe newRecipe) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        
        if (recipe.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (newRecipe == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Object currentLogInUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!Objects.equals(recipe.get().getUser().getId(), ((UserDetailsImp) currentLogInUser).getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        Recipe temp = Recipe.builder()
                .id(id)
                .name(newRecipe.getName() == null ? recipe.get().getName() : newRecipe.getName())
                .category(newRecipe.getCategory() == null ? recipe.get().getCategory() : newRecipe.getCategory())
                .description(newRecipe.getDescription() == null ? recipe.get().getDescription() : newRecipe.getDescription())
                .ingredients(newRecipe.getIngredients() == null ? recipe.get().getIngredients() : newRecipe.getIngredients())
                .directions(newRecipe.getDirections() == null ? recipe.get().getDirections() : newRecipe.getDirections())
                .user(((UserDetailsImp) currentLogInUser).getUser())
                .build();

        return recipeRepository.save(temp);
    }


}
