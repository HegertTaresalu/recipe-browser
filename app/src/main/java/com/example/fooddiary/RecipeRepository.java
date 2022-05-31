package com.example.fooddiary;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

public class RecipeRepository {
    private static final String API_KEY = "7afd0a94afae4a519d34cf9edd85f82b";
    private static final String URL = "https://api.spoonacular.com/recipes/%s/information?apiKey=%s";
    private final Application application;
    private final MutableLiveData<ArrayList<Recipe>> recipeLivedata;
    private  final ArrayList<Recipe> arrayList = new ArrayList<>();

    public RecipeRepository(Application application) {
        this.application = application;
        recipeLivedata = new MutableLiveData<>();
    }

    public void getRecipeInfo(String id) {
        Ion.with(application).load(String.format(URL, id, API_KEY)).asJsonObject().setCallback((e, result) -> {
            Log.i("recipe info: ", result.getAsJsonPrimitive("").toString());
            parseResults(result);
        });
    }

    private void parseResults(JsonObject result) {

        JsonObject dishTypes = (JsonObject) result.getAsJsonArray("dishTypes").get(0);

        int id = result.get("id").getAsInt();
        String title = result.get("title").getAsString();
        String dishType = dishTypes.get("dishTypes").getAsString();
        String sourceUrl = result.get("sourceUrl").getAsString();
        int readyIn = result.get("readyInMinutes").getAsInt();
        Boolean dairyFree = result.get("dairyFree").getAsBoolean();
        Boolean vegetarian = result.get("vegetarian").getAsBoolean();
        Boolean vegan = result.get("vegan").getAsBoolean();

        Recipe recipe = new Recipe(id, title, dishType, sourceUrl, readyIn, dairyFree, vegetarian, vegan);
        arrayList.add(recipe);
        recipeLivedata.setValue(arrayList);
    }
}



