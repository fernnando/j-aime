package co.fddittmar.j_aime.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import co.fddittmar.j_aime.R;
import co.fddittmar.j_aime.databinding.ItemRecipeBinding;
import co.fddittmar.j_aime.model.Recipe;
import co.fddittmar.j_aime.viewmodel.ItemRecipeViewModel;

/**
 * Recipes Adapter.
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipesAdapterViewHolder>{
    private List<Recipe> recipeList;

    public RecipesAdapter() {
        this.recipeList = Collections.emptyList();
    }

    @Override public RecipesAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemRecipeBinding itemRecipeBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_recipe,
                        parent, false);
        return new RecipesAdapterViewHolder(itemRecipeBinding);
    }

    @Override public void onBindViewHolder(RecipesAdapterViewHolder holder, int position) {
        holder.bindRecipe(recipeList.get(position));
    }

    @Override public int getItemCount() {
        return recipeList.size();
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
        notifyDataSetChanged();
    }

    public static class RecipesAdapterViewHolder extends RecyclerView.ViewHolder {
        ItemRecipeBinding mItemRecipeBinding;

        public RecipesAdapterViewHolder(ItemRecipeBinding itemRecipeBinding) {
            super(itemRecipeBinding.itemRecipe);
            this.mItemRecipeBinding = itemRecipeBinding;
        }

        void bindRecipe(Recipe recipe) {
            if (mItemRecipeBinding.getRecipeViewModel() == null) {
                mItemRecipeBinding.setRecipeViewModel(
                        new ItemRecipeViewModel(recipe, itemView.getContext()));
            } else {
                mItemRecipeBinding.getRecipeViewModel().setRecipe(recipe);
            }
        }
    }
}
