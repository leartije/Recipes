package recipes.repositor;

import org.springframework.data.jpa.repository.JpaRepository;
import recipes.entity.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {


}
