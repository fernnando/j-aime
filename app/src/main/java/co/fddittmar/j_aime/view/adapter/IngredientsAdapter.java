package co.fddittmar.j_aime.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import co.fddittmar.j_aime.R;
import co.fddittmar.j_aime.databinding.ItemIngredientBinding;
import co.fddittmar.j_aime.viewmodel.ItemIngredientViewModel;

/**
 * Ingredients Adapter.
 */

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsAdapterViewHolder> {
    private List<String> ingredientList;

    public IngredientsAdapter() {
        this.ingredientList = Collections.emptyList();
    }

    @Override public IngredientsAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemIngredientBinding itemIngredientBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_ingredient,
                        parent, false);
        return new IngredientsAdapterViewHolder(itemIngredientBinding);
    }

    @Override public void onBindViewHolder(IngredientsAdapterViewHolder holder, int position) {
        holder.bindIngredient(ingredientList.get(position));
    }

    @Override public int getItemCount() {
        return ingredientList.size();
    }

    public void setIngredientList(List<String> IngredientList) {
        this.ingredientList = IngredientList;
        notifyDataSetChanged();
    }

    public static class IngredientsAdapterViewHolder extends RecyclerView.ViewHolder {
        ItemIngredientBinding mItemIngredientBinding;

        public IngredientsAdapterViewHolder(ItemIngredientBinding itemIngredientBinding) {
            super(itemIngredientBinding.itemIngredient);
            this.mItemIngredientBinding = itemIngredientBinding;
        }

        void bindIngredient(String ingredient) {
            if (mItemIngredientBinding.getIngredientViewModel() == null) {
                mItemIngredientBinding.setIngredientViewModel(
                        new ItemIngredientViewModel(ingredient, itemView.getContext()));
            } else {
                mItemIngredientBinding.getIngredientViewModel().setIngredient(ingredient);
            }
        }
    }
}
