package co.fddittmar.j_aime.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import co.fddittmar.j_aime.model.Recipe;

/**
 * Created by Fernnando on 24/07/2017.
 */

public class ItemRecipeViewModel extends BaseObservable{
    private Recipe mRecipe;
    private Context mContext;

    public ItemRecipeViewModel(Recipe recipe , Context context) {
        mRecipe = recipe;
        mContext = context;
    }

    public String getTitle(){
        return mRecipe.getTitle();
    }

    public String getHref(){
        return mRecipe.getHref();
    }

    public List<String> getIngredients(){
        return mRecipe.getIngredients();
    }

    public String getThumbnail(){
        return mRecipe.getThumbnail();
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext()).load(imageUrl).into(view);
    }

}
