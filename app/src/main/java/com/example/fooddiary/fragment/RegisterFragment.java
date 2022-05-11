package com.example.fooddiary.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fooddiary.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterFragment extends Fragment implements View.OnClickListener {

    NavController navController;
    TextInputLayout firstName;
    TextInputLayout surName;
    TextInputLayout email;
    TextInputLayout username;
    TextInputLayout password;
    TextInputLayout passwordVerify;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        firstName = view.findViewById(R.id.inpLayFirstname);
        surName = view.findViewById(R.id.inpLaySurname);
        email= view.findViewById(R.id.inpLayEmail);
        username = view.findViewById(R.id.inpLayUsername);
        password = view.findViewById(R.id.inpLayPass);
        passwordVerify = view.findViewById(R.id.inpLayVerifyPass);
        view.findViewById(R.id.btnCompleteRegister).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Snackbar.make(view,firstName.getEditText().getText().toString(),Snackbar.LENGTH_SHORT).show();
        switch (view.getId()){
            case R.id.btnCompleteRegister:
                navController.navigate(R.id.action_registerFragment_to_loginFragment);
        }
    }
}