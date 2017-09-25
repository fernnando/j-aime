package co.fddittmar.j_aime.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import co.fddittmar.j_aime.R;
import co.fddittmar.j_aime.app.JaimeApplication;
import co.fddittmar.j_aime.network.RecipesService;
import co.fddittmar.j_aime.model.Recipe;
import co.fddittmar.j_aime.model.RecipeSearchResult;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Recipes View Model.
 */

public class RecipesViewModel extends Observable{

    public ObservableInt recipesProgress;
    public ObservableInt recipesRecycler;
    public ObservableInt recipesLabel;
    public ObservableField<String> messageLabel;

    private List<Recipe> recipesList;
    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public RecipesViewModel(@NonNull Context context) {

        this.context = context;
        this.recipesList = new ArrayList<>();
        recipesProgress = new ObservableInt(View.GONE);
        recipesRecycler = new ObservableInt(View.GONE);
        recipesLabel = new ObservableInt(View.VISIBLE);
        messageLabel = new ObservableField<>(context.getString(R.string.default_loading_recipes));
    }

    public void showProgress() {
        recipesLabel.set(View.GONE);
        recipesRecycler.set(View.GONE);
        recipesProgress.set(View.VISIBLE);
    }

    public void showListOfRecipes(){
        recipesProgress.set(View.GONE);
        recipesLabel.set(View.GONE);
        recipesRecycler.set(View.VISIBLE);
    }

    public void showMessage(){
        recipesProgress.set(View.GONE);
        recipesLabel.set(View.VISIBLE);
        recipesRecycler.set(View.GONE);
    }

    public void setNewMessage(String message){
        messageLabel.set(message);
    }

    /**
     * Update the list of recipes.
     * @param recipes to show in the recycler view.
     */
    private void updateRecipesList(List<Recipe> recipes) {
        recipesList.clear();
        recipesList.addAll(recipes);
        setChanged();
        notifyObservers();
    }

    public List<Recipe> getRecipesList() {
        return recipesList;
    }

    /**
     * Fetch and show the list of recipes that contains the query we are looking for. If any error
     * happens it will show an error message on the screen.
     * @param query that we are going to search in the API.
     */
    public void search(String query) {

        JaimeApplication jaimeApplication = JaimeApplication.create(context);
        RecipesService recipesService = jaimeApplication.getRecipesService();

        Disposable disposable = recipesService.searchRecipe(query)
                .subscribeOn(jaimeApplication.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RecipeSearchResult>() {
                    @Override public void accept(RecipeSearchResult recipeSearchResult) throws Exception {
                        updateRecipesList(recipeSearchResult.getRecipes());
                        showListOfRecipes();
                    }
                }, new Consumer<Throwable>() {
                    @Override public void accept(Throwable throwable) throws Exception {
                        setNewMessage(context.getString(R.string.error_loading_recipes));
                        showMessage();
                    }
                });

        compositeDisposable.add(disposable);
    }

    private void unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public void reset() {
        unSubscribeFromObservable();
        compositeDisposable = null;
        context = null;
    }
}
