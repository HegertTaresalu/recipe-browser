package com.example.fooddiary.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.fooddiary.AuthRepository;
import com.example.fooddiary.User;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class LoginViewModel extends AndroidViewModel {

    private final AuthRepository authRepository;
    private final MutableLiveData userMutableData;



    public LoginViewModel(@NonNull Application application) {
        super(application);
        authRepository = new AuthRepository(application);
        userMutableData = authRepository.getUserMutableLiveData();


    }
    public MutableLiveData getUserMutableLiveData(){

        return userMutableData;
    }

    public void userRegistration(String firstName, String surName, String email,String username,String password){
    authRepository.userRegistration(firstName,surName,email,username,password);

    }

}
