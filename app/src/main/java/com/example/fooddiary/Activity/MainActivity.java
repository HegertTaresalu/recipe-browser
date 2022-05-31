package com.example.fooddiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

//import com.example.fooddiary.utils.LanguageConfig;
import com.example.fooddiary.utils.SharedPrefs;

public class MainActivity extends AppCompatActivity {
    int NightMode;

    //vvv this is a java class vvv
    SharedPrefs sharedPrefs;
    //^^^ this is a java class ^^^

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor_theme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}