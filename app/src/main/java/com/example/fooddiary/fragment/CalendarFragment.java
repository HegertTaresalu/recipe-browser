package com.example.fooddiary.fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fooddiary.R;

import java.util.Calendar;

public class CalendarFragment extends Fragment implements View.OnClickListener {

    EditText title;
    EditText description;
    EditText personal;
    Button addRecipe;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title = view.findViewById(R.id.etTitle);
        description = view.findViewById(R.id.etDescription);
        personal = view.findViewById(R.id.etPersonal);
        view.findViewById(R.id.btnAddRecipe).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setData(CalendarContract.Events.CONTENT_URI);
        intent.putExtra(CalendarContract.Events.TITLE, title.getText().toString());
        intent.putExtra(CalendarContract.Events.DESCRIPTION, description.getText().toString() + "\n\nPersonal notes:\n\n" + personal.getText().toString());

        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getContext(), getString(R.string.error_intent), Toast.LENGTH_SHORT).show();
        }
    }
}