package co.fddittmar.j_aime.data.remote;

import java.io.IOException;

import co.fddittmar.j_aime.model.RecipeSeachResult;
import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RecipesParser {

    public static Observable<RecipeSeachResult> searchByTitle(String q) throws IOException {
        RxJavaCallAdapterFactory rxAdapter =
                RxJavaCallAdapterFactory.createWithScheduler(rx.schedulers.Schedulers.io());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.omdbapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .build();

        RecipesRetrofit api = retrofit.create(RecipesRetrofit.class);

        return api.searchRecipe(q);
    }
}
