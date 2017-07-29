package co.fddittmar.j_aime.data.remote;

import co.fddittmar.j_aime.model.RecipeSeachResult;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface RecipesRetrofit {
    @GET("./")
    Observable<RecipeSeachResult> searchRecipe(@Query("q") String q);
}
