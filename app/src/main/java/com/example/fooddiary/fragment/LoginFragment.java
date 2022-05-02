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

public class LoginFragment extends Fragment implements View.OnClickListener {

    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        view.findViewById(R.id.btnSignIn).setOnClickListener(this);
        view.findViewById(R.id.txtForgotPass).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSignIn:
                navController.navigate(R.id.action_loginFragment_to_verifiedFragment);
                break;
            case R.id.txtForgotPass:
                navController.navigate(R.id.action_loginFragment_to_recoveryFragment2);
                break;
        }
    }
}