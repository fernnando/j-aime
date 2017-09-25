package co.fddittmar.j_aime.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Observable;
import java.util.Observer;

import co.fddittmar.j_aime.R;
import co.fddittmar.j_aime.databinding.ActivityRecipesBinding;
import co.fddittmar.j_aime.view.adapter.RecipesAdapter;
import co.fddittmar.j_aime.viewmodel.RecipesViewModel;

public class RecipesActivity extends AppCompatActivity implements Observer {

    private ActivityRecipesBinding activityRecipesBinding;
    private RecipesViewModel recipesViewModel;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupDataBinding();
        setSupportActionBar(activityRecipesBinding.toolbar);
        setupListRecipesView(activityRecipesBinding.listRecipes);
        setupObserver(recipesViewModel);
    }

    /**
     * Setup data binding.
     */
    private void setupDataBinding() {
        activityRecipesBinding = DataBindingUtil.setContentView(this, R.layout.activity_recipes);
        recipesViewModel = new RecipesViewModel(this);
        activityRecipesBinding.setRecipesViewModel(recipesViewModel);
    }

    /**
     * Setup the recipe list to the activity's recycler view
     */
    private void setupListRecipesView(RecyclerView recipesList) {
        RecipesAdapter adapter = new RecipesAdapter();
        recipesList.setAdapter(adapter);
        recipesList.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        recipesViewModel.reset();
    }

    /**
     * Add to toolbar the search action
     * @param menu containing the actions
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    /**
     * Method to send to the ViewModel the query we want to search in the API.
     * @param searchView where we are going to get the query to do the search.
     */
    private void search(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                recipesViewModel.showProgress();
                recipesViewModel.search(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
    }

    /**
     * Method to update the list of recipes when there's an event.
     * @param observable is the observable object that tell us that a change was made.
     * @param data optional param from notifyObservers() method.
     */
    @Override public void update(Observable observable, Object data) {
        if (observable instanceof RecipesViewModel) {
            RecipesAdapter recipesAdapter = (RecipesAdapter) activityRecipesBinding.listRecipes.getAdapter();
            RecipesViewModel recipesViewModel = (RecipesViewModel) observable;
            recipesAdapter.setRecipeList(recipesViewModel.getRecipesList());
        }
    }
}
