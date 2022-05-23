package com.example.fooddiary.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BrowserViewModel extends ViewModel{

    private final MutableLiveData<String> mText;

    public BrowserViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the browser fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
