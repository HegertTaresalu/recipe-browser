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
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.fooddiary.Activity.MainActivity;
import com.example.fooddiary.R;
public class SettingsFragment extends Fragment implements View.OnClickListener {
    NavController navController;
    Button theme;
    Button language;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btnTheme).setOnClickListener(this);
        view.findViewById(R.id.btnLang).setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        navController = Navigation.findNavController(view);
        switch (view.getId()){
                case R.id.btnLang:
                    navController.navigate(R.id.action_nav_settings_to_languageFragment);
                    break;
                case R.id.btnTheme:
                    navController.navigate(R.id.action_nav_settings_to_themeFragment);
                    break;
            }
    }
}