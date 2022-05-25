package com.example.fooddiary.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.fooddiary.MainActivity;
import com.example.fooddiary.R;
public class SettingsFragment extends Fragment implements View.OnClickListener {
    NavController navController;
    Switch themeSwitch;
    private Boolean isChecked;
    MainActivity mainActivity;

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
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    @Override
    public void onClick(View view) {
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