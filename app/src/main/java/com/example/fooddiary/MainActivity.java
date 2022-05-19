package com.example.fooddiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    int NightMode;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        sharedPreferences = getSharedPreferences("SharedPrefs", MODE_PRIVATE);
        NightMode = sharedPreferences.getInt("NightModeInt", 1);
        AppCompatDelegate.setDefaultNightMode(NightMode);

        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        NightMode = AppCompatDelegate.getDefaultNightMode();

        sharedPreferences = getSharedPreferences("SharedPrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        editor.putInt("NightModeInt", NightMode);
        editor.apply();
    }
    public void switchTheme(View view){

        RadioButton darkTheme = view.findViewById(R.id.darkModeBtn);
        RadioButton lightTheme = view.findViewById(R.id.lightModeBtn);
        RadioButton systemDefault= view.findViewById(R.id.systemDefaultBtn);

        if (darkTheme.isChecked()){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        }
        else if (lightTheme.isChecked()){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        }
        else if (systemDefault.isChecked()){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

        }

    }


}