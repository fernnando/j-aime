package co.fddittmar.j_aime.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import co.fddittmar.j_aime.R;
import co.fddittmar.j_aime.databinding.ActivityRecipeDetailBinding;
import co.fddittmar.j_aime.model.Recipe;
import co.fddittmar.j_aime.view.adapter.IngredientsAdapter;
import co.fddittmar.j_aime.viewmodel.RecipeDetailViewModel;

/**
 * Activity to show details of the Recipe.
 */

public class RecipeDetailActivity extends AppCompatActivity {

    private static final String EXTRA_RECIPE = "EXTRA_RECIPE";

    private ActivityRecipeDetailBinding recipeDetailActivityBinding;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipeDetailActivityBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_recipe_detail);
        setSupportActionBar(recipeDetailActivityBinding.toolbar);
        displayHomeAsUpEnabled();
        getExtrasFromIntent();
    }

    /**
     * Get the data from the recipe item clicked on 'RecipesActivity' list.
     */
    private void getExtrasFromIntent() {
        Recipe recipe = (Recipe) getIntent().getSerializableExtra(EXTRA_RECIPE);
        RecipeDetailViewModel recipeDetailViewModel = new RecipeDetailViewModel(recipe);

        //Setting up the ingredients list
        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter();
        recipeDetailActivityBinding.listIngredients.setAdapter(ingredientsAdapter);
        recipeDetailActivityBinding.listIngredients.setLayoutManager(new LinearLayoutManager(this));
        ingredientsAdapter = (IngredientsAdapter) recipeDetailActivityBinding.listIngredients.getAdapter();
        ingredientsAdapter.setIngredientList(recipeDetailViewModel.getIngredientsList());

        recipeDetailActivityBinding.setRecipeDetailViewModel(recipeDetailViewModel);
        setTitle(recipe.title);
    }

    /**
     * Show us the recipe's details that come from the ItemRecipeViewModel.
     * @param context our activity context.
     * @param recipe item that we clicked on RecipesActivity.
     * @return an intent with the recipe's details.
     */
    public static Intent launchDetail(Context context, Recipe recipe) {
        Intent intent = new Intent(context, RecipeDetailActivity.class);
        intent.putExtra(EXTRA_RECIPE, recipe);
        return intent;
    }

    /**
     * Add the 'back home' option in the activity
     */
    private void displayHomeAsUpEnabled() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
