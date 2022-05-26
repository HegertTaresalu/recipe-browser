package com.example.fooddiary.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.fooddiary.R;
import com.example.fooddiary.models.Recipe;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

public class RecipeRepository {
    private static final String API_KEY = "fe722c39854643239dc98f5ef524d6a2";
    private static final String URL = "https://api.spoonacular.com/recipes/random?number=8&apiKey=%s";
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
        JsonArray recipes =  result.getAsJsonArray("recipes");


        for (int i = 0; i < recipes.size() ; i++) {
            String type;
            Log.i("recipes", String.valueOf(recipes.size()));
            Log.i("recipes", String.valueOf(i));

                //weird bug that happens by chance
                //sometimes when starting the application from start and choosing browser from menu
                //the application has to restart the view and/or it crashes
                //it maybe happens because api is slow also probably because we haven't implemented multiple threads
                Log.i("reciperepo","reciperepos");
                JsonObject recipe = (JsonObject) recipes.get(i);

                int id = recipe.get("id").getAsInt();
                String title = recipe.get("title").getAsString();
                //String dishType = dishTypes.get("dishTypes").getAsString();
                String sourceUrl = recipe.get("sourceUrl").getAsString();
                int readyIn = recipe.get("readyInMinutes").getAsInt();
                 type = application.getString(R.string.dishtype);
            if (recipe.has("dishTypes")){
                    JsonArray dishTypes =  recipe.getAsJsonArray("dishTypes");
                     type = dishTypes.get(0).getAsString();
                }

                Boolean dairyFree = recipe.get("dairyFree").getAsBoolean();
                Boolean vegetarian = recipe.get("vegetarian").getAsBoolean();
                Boolean vegan = recipe.get("vegan").getAsBoolean();

                Recipe final_recipe = new Recipe(id, title, type, sourceUrl, readyIn, dairyFree, vegetarian, vegan);
                arrayList.add(final_recipe);
                Log.i("gaming", String.valueOf(final_recipe.getDishType()));
                recipeLiveData.setValue(arrayList);

        }

    }
}



