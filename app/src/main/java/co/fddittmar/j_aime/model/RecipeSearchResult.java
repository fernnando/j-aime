package co.fddittmar.j_aime.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Model that represents the result data from the API.
 */

public class RecipeSearchResult implements Serializable {

    @SerializedName("results")
    private List<Recipe> recipes;


    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
