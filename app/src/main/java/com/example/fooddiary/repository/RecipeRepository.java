package com.example.fooddiary.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.fooddiary.models.Recipe;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

public class RecipeRepository {
    private static final String API_KEY = "fe722c39854643239dc98f5ef524d6a2";
    private static final String URL = "https://api.spoonacular.com/recipes/random?number=10&apiKey=%s";
    private final Application application;
    private final MutableLiveData<ArrayList<Recipe>> recipeLiveData;
    private  final ArrayList<Recipe> arrayList = new ArrayList<>();

    public RecipeRepository(Application application) {
        this.application = application;
        recipeLiveData = new MutableLiveData<>();
    }

    public void getRecipeInfo(/*int id*/) {
        if (arrayList.size() < 10)
        {
            Ion.with(application).load(String.format(URL, /*id,*/ API_KEY)).asJsonObject().setCallback((e, result) -> {
                Log.i("gaming",String.format(URL,/* id,*/ API_KEY));
                parseResults(result);
            });
        }

    }
    public MutableLiveData<ArrayList<Recipe>> getRecipeLiveData(){return recipeLiveData;}


    private void parseResults(JsonObject result) {

        for (int i = 0; i < 10 ; i++) {
            JsonObject recipes = (JsonObject) result.getAsJsonArray("recipes").get(i);
            JsonArray dishTypes =  recipes.getAsJsonArray("dishTypes");

            String type = dishTypes.get(0).getAsString();
            int id = recipes.get("id").getAsInt();
            String title = recipes.get("title").getAsString();
            //String dishType = dishTypes.get("dishTypes").getAsString();
            String sourceUrl = recipes.get("sourceUrl").getAsString();
            int readyIn = recipes.get("readyInMinutes").getAsInt();
            Boolean dairyFree = recipes.get("dairyFree").getAsBoolean();
            Boolean vegetarian = recipes.get("vegetarian").getAsBoolean();
            Boolean vegan = recipes.get("vegan").getAsBoolean();

            Recipe recipe = new Recipe(id, title, type, sourceUrl, readyIn, dairyFree, vegetarian, vegan);
            Log.i("gaming", String.valueOf(recipe.getDishType()));
            arrayList.add(recipe);
            recipeLiveData.setValue(arrayList);

        }


    }
}



