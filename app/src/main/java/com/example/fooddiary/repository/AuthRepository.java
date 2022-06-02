package com.example.fooddiary.repository;

import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.fooddiary.R;
import com.example.fooddiary.models.Recipe;
import com.example.fooddiary.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AuthRepository {

    public static final String TAG = "Firebase";

    private final FirebaseAuth firebaseAuth;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final Application application;
    private final MutableLiveData<FirebaseUser> userMutableLiveData;
    private final MutableLiveData<Boolean> loggedOutMutableLiveData;
    private final MutableLiveData<ArrayList<User>> userLiveData;


    public AuthRepository(Application application) {
        this.application = application;
        userMutableLiveData = new MutableLiveData<>();
        loggedOutMutableLiveData = new MutableLiveData<>();
        firebaseAuth = FirebaseAuth.getInstance();
        userLiveData = new MutableLiveData<>();


        if (firebaseAuth.getCurrentUser() != null) {
            userMutableLiveData.postValue(firebaseAuth.getCurrentUser());
            loggedOutMutableLiveData.postValue(false);
        }

    }

    //method for registering email
    public void userRegistration(String firstName, String lastName, String email, String username, String password) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(application.getMainExecutor(), task -> {

                        if (task.isSuccessful()) {
                            if (firebaseAuth.getCurrentUser() != null) {

                                String userId = firebaseAuth.getCurrentUser().getUid();
                                //Creates new collection named users if one doesn't exist into it add a new document UID reference
                                DocumentReference documentReference = db.collection("users").document(userId);
                                Map<String, Object> user = new HashMap<>();
                                user.put("firstName", firstName);
                                user.put("surname", lastName);
                                user.put("email", email);
                                user.put("username", username);
                                documentReference.set(user).addOnSuccessListener(aVoid -> Log.i(TAG, "onSuccess:user data was saved"))
                                        .addOnFailureListener(e -> Log.e(TAG, "onFailure: error writing to db"));
                                userMutableLiveData.postValue(firebaseAuth.getCurrentUser());
                            }
                        } else {
                            Toast.makeText(application, application.getString(R.string.error, task.getException().getMessage()), Toast.LENGTH_SHORT).show();
                        }

                    });
        }

    }

    public void saveRecipe(Bundle args){
        String userId = firebaseAuth.getCurrentUser().getUid();
        Map<String, Object> recipe = new HashMap<>();

        recipe.put("title",args.getString("recipe_title"));
        recipe.put("recipe_Type",args.getString("recipe_type"));
        recipe.put("url",args.getString("recipe_src_url"));
        recipe.put("preptime",args.getInt("prep_time"));
        recipe.put("isDairyFree",args.getBoolean("isDairy"));
        recipe.put("isVegetarian",args.getBoolean("isVegetarian"));
        recipe.put("isVegan",args.getBoolean("isVegan"));

        db.collection("recipes").document(userId).collection("recipes").document(String.valueOf(args.getInt("id"))).set(recipe)
        .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.i(TAG, "DocumentSnapshot successfully written!");
            }
        });

    }



        public void logOut(){
            firebaseAuth.signOut();
            loggedOutMutableLiveData.postValue(true);

        }

        public void logIn (String email, String password){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(application.getMainExecutor(), task -> {
                            if (task.isSuccessful()) {
                                userMutableLiveData.postValue(firebaseAuth.getCurrentUser());
                            } else {
                                // Toast.makeText(application, application.getString(R.string.error, task.getException().getMessage()), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }


        public void resetPassword (String email){
            firebaseAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "Email sent.");
                            }
                        }
                    });
        }


        public MutableLiveData<FirebaseUser> getUserMutableLiveData () {
            return userMutableLiveData;
        }

        public MutableLiveData<Boolean> getLoggedOutMutableLiveData () {
            return loggedOutMutableLiveData;
        }

        public MutableLiveData<ArrayList<User>> getUserLiveData () {
            return userLiveData;
        }
}




