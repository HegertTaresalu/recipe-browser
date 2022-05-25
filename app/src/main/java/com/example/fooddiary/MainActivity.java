package com.example.fooddiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.example.fooddiary.utils.LanguageConfig;

public class MainActivity extends AppCompatActivity {
    int NightMode;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor_theme;

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
        editor_theme = sharedPreferences.edit();

        editor_theme.putInt("NightModeInt", NightMode);
        editor_theme.apply();
    }

    public void switchTheme(View view, SharedPreferences.Editor editor ){
        RadioButton darkTheme = view.findViewById(R.id.darkModeBtn);
        RadioButton lightTheme = view.findViewById(R.id.lightModeBtn);
        RadioButton systemDefault= view.findViewById(R.id.systemDefaultBtn);
        if (darkTheme.isChecked()){
            editor.putInt("checkedSP",0);
            editor.commit();
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else if (lightTheme.isChecked()){
            editor.putInt("checkedSP",1);
            editor.commit();

            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        else if (systemDefault.isChecked()){
            editor.putInt("checkedSP",2);
            editor.commit();
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        String languageCode = "en";
        Context context = LanguageConfig.changeLanguage(newBase, languageCode);
        super.attachBaseContext(newBase);
    }
}