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
import android.widget.Button;

import com.example.fooddiary.R;
import com.example.fooddiary.ViewModel.LoginViewModel;

public class VerifiedFragment extends Fragment implements View.OnClickListener {

    NavController navController;
    Button btnCalendar;
    LoginViewModel loginViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_verified, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        view.findViewById(R.id.btnCalendar).setOnClickListener(this);
        view.findViewById(R.id.log_out).setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCalendar:
                navController.navigate(R.id.action_verifiedFragment_to_calendarFragment);
                break;
            case  R.id.log_out:
                loginViewModel.logout();
                navController.navigate(R.id.action_verifiedFragment_to_mainFragment);
        }
    }
}