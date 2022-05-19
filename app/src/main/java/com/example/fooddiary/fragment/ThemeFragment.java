package com.example.fooddiary.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.fooddiary.MainActivity;
import com.example.fooddiary.R;
import com.google.android.material.snackbar.Snackbar;

public class ThemeFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {

    RadioGroup themes;
    MainActivity mainActivity;
    Integer selectedBtnId;
    Button selectedBtn;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
        if (savedInstanceState != null ){
            SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            selectedBtnId = savedInstanceState.getInt("radioBtn");
            editor.putInt("radioBtnId", selectedBtnId);
            editor.apply();
            Log.i("oncreate", String.valueOf(selectedBtnId));
        }


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_theme, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        themes = view.findViewById(R.id.radioGroup);
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);



        if (sharedPref.getInt("radioBtnId",0) != 0){
            themes.check(sharedPref.getInt("radioBtn",0));

        }
        themes.setOnCheckedChangeListener(this);
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        themes.getCheckedRadioButtonId();
        Log.i("gaming", String.valueOf(themes.getCheckedRadioButtonId()));
        ((MainActivity) ThemeFragment.this.getActivity()).switchTheme(getView());

    }


    public void onSaveInstanceState(Bundle savedInstanceState) {
         savedInstanceState.putInt("radioBtn",themes.getCheckedRadioButtonId());
        super.onSaveInstanceState(savedInstanceState);
    }


}