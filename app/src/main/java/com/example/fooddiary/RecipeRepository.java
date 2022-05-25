package com.example.fooddiary;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

public class RecipeRepository {
    private static final String API_KEY = "fe722c39854643239dc98f5ef524d6a2";
    private static final String URL = "https://api.spoonacular.com/recipes/%s/information?apiKey=%s";
    private final Application application;
    private final MutableLiveData<ArrayList<Recipe>> recipeLiveData;
    private  final ArrayList<Recipe> arrayList = new ArrayList<>();

    public RecipeRepository(Application application) {
        this.application = application;
        recipeLiveData = new MutableLiveData<>();
    }

    public void getRecipeInfo(int id) {
        Ion.with(application).load(String.format(URL, id, API_KEY)).asJsonObject().setCallback((e, result) -> {
            Log.i("gaming",String.format(URL, id, API_KEY));
            parseResults(result);
        });
    }
    public MutableLiveData<ArrayList<Recipe>> getRecipeLiveData(){return recipeLiveData;}


    private void parseResults(JsonObject result) {

        JsonArray dishTypes =  result.getAsJsonArray("dishTypes");

        String type = dishTypes.get(0).getAsString();
        int id = result.get("id").getAsInt();
        String title = result.get("title").getAsString();
        //String dishType = dishTypes.get("dishTypes").getAsString();
        String sourceUrl = result.get("sourceUrl").getAsString();
        int readyIn = result.get("readyInMinutes").getAsInt();
        Boolean dairyFree = result.get("dairyFree").getAsBoolean();
        Boolean vegetarian = result.get("vegetarian").getAsBoolean();
        Boolean vegan = result.get("vegan").getAsBoolean();

        Recipe recipe = new Recipe(id, title, type, sourceUrl, readyIn, dairyFree, vegetarian, vegan);
        Log.i("gaming", String.valueOf(recipe.getDishType()));
        arrayList.add(recipe);
        recipeLiveData.setValue(arrayList);
    }
}



