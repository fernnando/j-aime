package co.fddittmar.j_aime.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Fernnando on 24/07/2017.
 */

public class RecipeSeachResult implements Serializable {
    @SerializedName("title")
    private String title;
    @SerializedName("version")
    private String version;
    @SerializedName("href")
    private String href;
    @SerializedName("results")
    private List<Recipe> recipes;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
