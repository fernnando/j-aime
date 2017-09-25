package co.fddittmar.j_aime.network;

import co.fddittmar.j_aime.model.RecipeSearchResult;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface RecipesService {
    @GET("./") //i.e. http://www.recipepuppy.com/api/?
    Observable<RecipeSearchResult> searchRecipe(@Query("q") String q);
}
