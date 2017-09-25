package co.fddittmar.j_aime.view.adapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import co.fddittmar.j_aime.R;

/**
 * Binding adapter to load images into image views.
 */

public class CustomBindingAdapter {
    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        if(imageUrl.isEmpty())
            view.setImageResource(R.drawable.default_recipe);
        else
            Glide.with(view.getContext()).load(imageUrl).into(view);
    }
}
