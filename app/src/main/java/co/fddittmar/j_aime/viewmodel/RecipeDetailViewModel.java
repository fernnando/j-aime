package co.fddittmar.j_aime.viewmodel;

import java.util.Arrays;
import java.util.List;

import co.fddittmar.j_aime.model.Recipe;

/**
 * Recipe detail View Model.
 */

public class RecipeDetailViewModel {
    private Recipe recipe;

    public RecipeDetailViewModel(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getTitle() {
        return recipe.title;
    }

    public String getHref() {
        return recipe.href;
    }

    public List<String> getIngredientsList() {
        return Arrays.asList((recipe.ingredients.replaceAll(" ","")).split(","));
    }

    public String getPicture() {
        return recipe.picture;
    }
}
