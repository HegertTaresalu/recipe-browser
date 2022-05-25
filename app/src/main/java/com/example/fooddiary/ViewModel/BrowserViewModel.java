package com.example.fooddiary.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooddiary.RecipeRepository;

public class BrowserViewModel extends ViewModel{

    private final RecipeRepository repository;
    private final MutableLiveData<String> mText;


    public BrowserViewModel(@NonNull Application application) {
        //super(application);
        repository = new RecipeRepository(application);
        mText = new MutableLiveData<>();
        mText.setValue("This is the browser fragment");
    }
    //public void getRecipe() { repository; }
    public LiveData<String> getText() {
        return mText;
    }
}