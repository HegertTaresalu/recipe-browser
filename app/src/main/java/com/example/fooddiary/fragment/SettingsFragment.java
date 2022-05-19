package com.example.fooddiary.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.fooddiary.MainActivity;
import com.example.fooddiary.R;
public class SettingsFragment extends Fragment {
Switch themeSwitch;
private Boolean isChecked;
MainActivity mainActivity;
    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();

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
        themeSwitch = view.findViewById(R.id.themeSwitch);
     SharedPreferences sharedPrefs = mainActivity.getSharedPreferences("com.example.xyle", MODE_PRIVATE);
      themeSwitch.setChecked(sharedPrefs.getBoolean("NameOfThingToSave", true));
        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                //SharedPreferences.Editor editor =mainActivity.getSharedPreferences("com.example.xyz", MODE_PRIVATE).edit();
                //editor.putBoolean("NameOfThingToSave", isChecked);
                //editor.commit();
                //((MainActivity) SettingsFragment.this.getActivity()).switchTheme();
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("switch",isChecked);
        super.onSaveInstanceState(outState);
    }

}