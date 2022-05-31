package com.example.fooddiary.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.fooddiary.Activity.MainActivity;
import com.example.fooddiary.R;

public class ThemeFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {

    RadioGroup themes;
    MainActivity mainActivity;
    Integer selectedBtnId;
    Button selectedBtn;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
            sharedPref = getActivity().getSharedPreferences("pref",0);
            editor = sharedPref.edit();
            Log.i("oncreate", String.valueOf(selectedBtnId));



    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_theme, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        int checkedSP = sharedPref.getInt("checkedSP",3);

        RadioButton darkTheme = view.findViewById(R.id.darkModeBtn);
        RadioButton lightTheme = view.findViewById(R.id.lightModeBtn);
        RadioButton systemDefault= view.findViewById(R.id.systemDefaultBtn);

        if (checkedSP == 0 ){
            darkTheme.setChecked(true);
        }
        else if(checkedSP == 1){
            lightTheme.setChecked(true);
        }
        else if (checkedSP == 2){
            systemDefault.setChecked(true);
        }

        themes = view.findViewById(R.id.radioGroup);
        themes.setOnCheckedChangeListener(this);


    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        Log.i("gaming", String.valueOf(themes.getCheckedRadioButtonId()));
        ((MainActivity) ThemeFragment.this.getActivity()).switchTheme(getView(),editor);

    }




}