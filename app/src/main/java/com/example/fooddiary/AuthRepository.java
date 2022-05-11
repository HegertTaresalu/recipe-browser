package com.example.fooddiary;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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




    public AuthRepository(Application application){
        this.application = application;
        userMutableLiveData = new MutableLiveData<>();
        loggedOutMutableLiveData = new MutableLiveData<>();
        firebaseAuth = FirebaseAuth.getInstance();
        userLiveData = new MutableLiveData<>();


        if (firebaseAuth.getCurrentUser()!= null){
            userMutableLiveData.postValue(firebaseAuth.getCurrentUser());
            loggedOutMutableLiveData.postValue(false);
            //loadUserData();
        }

    }

    //method for registering email
    @SuppressLint("NewApi")
    public void userRegistration(String firstName, String lastName, String email,String username, String password){
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(application.getMainExecutor(), task -> {

                    if (task.isSuccessful()) {
                        if (firebaseAuth.getCurrentUser() != null) {

                            String userId = firebaseAuth.getCurrentUser().getUid();
                            //Creates new collection named users if one doesn't exist into it add a new document UID reference
                            DocumentReference documentReference = db.collection("users").document(userId);
                            Map<String, Object> user = new HashMap<>();
                            user.put("firstName", firstName);
                            user.put("lastName", lastName);
                            user.put("email", email);
                            user.put("username",username);
                            documentReference.set(user).addOnSuccessListener(aVoid -> Log.i(TAG, "onSuccess:user data was saved"))
                                    .addOnFailureListener(e -> Log.e(TAG, "onFailure: error writing to db"));
                            userMutableLiveData.postValue(firebaseAuth.getCurrentUser());
                        }
                    }
                    else{
                        Toast.makeText(application, application.getString(R.string.error, task.getException().getMessage()),Toast.LENGTH_SHORT).show();
                    }

                });

    }


    public void logOut(){
        firebaseAuth.signOut();
        loggedOutMutableLiveData.postValue(true);

    }

    @SuppressLint("NewApi")
    public void logIn(String email, String password){
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(application.getMainExecutor(), task -> {
                    if (task.isSuccessful()) {
                        userMutableLiveData.postValue(firebaseAuth.getCurrentUser());
                    }
                    else{
                        Toast.makeText(application, application.getString(R.string.error, task.getException().getMessage()), Toast.LENGTH_SHORT).show();
                    }
                });

    }


    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }

    public MutableLiveData<Boolean> getLoggedOutMutableLiveData() {
        return loggedOutMutableLiveData;
    }

    public MutableLiveData<ArrayList<User>> getUserLiveData() {
        return userLiveData;
    }
}





