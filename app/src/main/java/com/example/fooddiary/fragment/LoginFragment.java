package com.example.fooddiary.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fooddiary.R;
import com.example.fooddiary.ViewModel.LoginViewModel;
import com.example.fooddiary.ViewModel.UserViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class LoginFragment extends Fragment implements View.OnClickListener {

    private LoginViewModel loginViewModel;
    private UserViewModel userViewModel;
    private NavController navController;
    private TextInputLayout emailInput;
    private TextInputLayout passwordInput;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getUserMutableLiveData().observe(this, firebaseUser -> {
            if (firebaseUser != null){
                Toast.makeText(getContext(),"User logged in", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        emailInput = view.findViewById(R.id.inpLayEmail);
        passwordInput = view.findViewById(R.id.inpLayPass);
        view.findViewById(R.id.btnSignIn).setOnClickListener(this);
        view.findViewById(R.id.txtForgotPass).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSignIn:
                String email = Objects.requireNonNull(emailInput.getEditText()).getText().toString();
                String password = Objects.requireNonNull(passwordInput.getEditText()).getText().toString();
                loginViewModel.Login(email,password);
                navController.navigate(R.id.action_loginFragment_to_verifiedFragment);
                break;
            case R.id.txtForgotPass:
                navController.navigate(R.id.action_loginFragment_to_recoveryFragment2);
                break;
        }
    }
}