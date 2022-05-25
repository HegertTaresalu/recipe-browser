package com.example.fooddiary.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.fooddiary.Recipe;
import com.example.fooddiary.RecipeRepository;

import java.util.ArrayList;

public class BrowserViewModel extends AndroidViewModel{

    private final RecipeRepository repository;
    private final MutableLiveData<ArrayList<Recipe>> recipeLiveData;

    public BrowserViewModel(@NonNull Application application) {
       super(application);
       repository = new RecipeRepository(application);
       recipeLiveData = repository.getRecipeLiveData();
    }

    public void getRecipeData(){repository.getRecipeInfo(15233);}

    public MutableLiveData<ArrayList<Recipe>> getRecipeLiveData(){
        return recipeLiveData;
    }
}