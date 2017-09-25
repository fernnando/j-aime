package co.fddittmar.j_aime.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;

/**
 * Ingredient item View Model
 */

public class ItemIngredientViewModel extends BaseObservable {
    private String ingredient;
    private Context context;

    public ItemIngredientViewModel(String ingredient, Context context) {
        this.ingredient = ingredient;
        this.context = context;
    }

    public String getIngredient() {
        return ingredient;
    }


    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
        notifyChange();
    }

}
