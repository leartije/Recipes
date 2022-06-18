package recipes.repositor;

import recipes.entity.Recipe;

import java.util.HashMap;
import java.util.Map;

public interface RecipeRepository {
    Map<Long, Recipe> data = new HashMap<>();

}
