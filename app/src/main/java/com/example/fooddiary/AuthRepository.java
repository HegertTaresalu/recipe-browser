package com.example.fooddiary;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class AuthRepository {

    private final FirebaseAuth firebaseAuth;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private final Application application;



    public AuthRepository(Application application){
        this.application =application;
        firebaseAuth = FirebaseAuth.getInstance();
    }



}
