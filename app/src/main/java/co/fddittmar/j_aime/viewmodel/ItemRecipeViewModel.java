package co.fddittmar.j_aime.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;

import co.fddittmar.j_aime.model.Recipe;
import co.fddittmar.j_aime.view.RecipeDetailActivity;

/**
 * Recipe item View Model
 */

public class ItemRecipeViewModel extends BaseObservable{
    private Recipe recipe;
    private Context context;

    public ItemRecipeViewModel(Recipe recipe, Context context) {
        this.recipe = recipe;
        this.context = context;
    }

    public String getTitle() {
        return recipe.title;
    }

    public String getHref() {
        return recipe.href;
    }

    public String getIngredients() {
        return recipe.ingredients;
    }

    public String getPicture() {

        return recipe.picture;
    }

    public void onItemClick(View view) {
        context.startActivity(RecipeDetailActivity.launchDetail(view.getContext(), recipe));
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
        notifyChange();
    }

}
