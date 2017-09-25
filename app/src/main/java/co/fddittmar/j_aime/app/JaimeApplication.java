package co.fddittmar.j_aime.app;

import android.app.Application;
import android.content.Context;

import co.fddittmar.j_aime.network.RecipesParser;
import co.fddittmar.j_aime.network.RecipesService;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Fernnando on 21/09/2017.
 */

public class JaimeApplication extends Application{
    private RecipesService recipesService;
    private Scheduler scheduler;

    private static JaimeApplication get(Context context) {
        return (JaimeApplication) context.getApplicationContext();
    }

    public static JaimeApplication create(Context context) {
        return JaimeApplication.get(context);
    }

    public RecipesService getRecipesService() {
        if (recipesService == null) {
            recipesService = RecipesParser.create();
        }

        return recipesService;
    }

    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }

        return scheduler;
    }

    public void setRecipesService(RecipesService recipesService) {
        this.recipesService = recipesService;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}
