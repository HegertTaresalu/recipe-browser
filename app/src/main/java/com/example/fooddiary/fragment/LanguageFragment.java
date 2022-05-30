package com.example.fooddiary.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.fooddiary.MainActivity;
import com.example.fooddiary.NavbarActivity;
import com.example.fooddiary.R;

import java.util.Locale;

public class LanguageFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {
    NavbarActivity navbarActivity;
    NavController navController;
    RadioGroup languages;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    Locale locale;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navbarActivity = (NavbarActivity) getActivity();
        sharedPref = getActivity().getSharedPreferences("pref",0);
        editor = sharedPref.edit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_language, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        navController = Navigation.findNavController(view);

        languages = view.findViewById(R.id.rgLanguages);

        languages.setOnCheckedChangeListener(this);

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        View view = getView();
        int checkedSP = sharedPref.getInt("checkedSP",3);
        RadioButton english = view.findViewById(R.id.btnEng);
        RadioButton estonian = view.findViewById(R.id.btnEst);
        RadioButton welsh = view.findViewById(R.id.btnCym);

        switch (checkedId) {
            case R.id.btnEng:
                locale = new Locale("en");
                changeChecked(english);
                break;
            case  R.id.btnEst:
                locale = new Locale("et");
                changeChecked(estonian);
                break;
            case  R.id.btnCym:
                locale = new Locale("cy");
                changeChecked(welsh);
                break;
            default:
                locale = new Locale("en");
                break;
        }

        ((NavbarActivity) LanguageFragment.this.getActivity()).switchLang(getView(),editor);

    }

    public void changeChecked(RadioButton rb) {
        rb.setChecked(true);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        navbarActivity.getBaseContext().getResources().updateConfiguration(configuration,
                navbarActivity.getBaseContext().getResources().getDisplayMetrics());

        Intent i = new Intent();
        i.setClass(getActivity(), NavbarActivity.class);
        startActivity(i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK));
    }

}