package com.example.fooddiary.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fooddiary.R;
import com.example.fooddiary.RecipeAdapter;
import com.example.fooddiary.ViewModel.BrowserViewModel;

public class BookmarkFragment extends Fragment {
    private RecipeAdapter recipeAdapter;
    BrowserViewModel browserViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_bookmark, container, false);
        browserViewModel = new ViewModelProvider(this).get(BrowserViewModel.class);

        Log.i("gaming", String.valueOf(browserViewModel.getBookMarkedRecipeLiveData()));
        browserViewModel.getData();
        browserViewModel.getBookMarkedRecipeLiveData()
                .observe(getViewLifecycleOwner(),recipes ->
                        recipeAdapter.setRecipeList(recipes)
                );



        RecyclerView recyclerView = view.findViewById(R.id.bookMarkRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setHasFixedSize(false);
        recipeAdapter = new RecipeAdapter();
        recyclerView.setAdapter(recipeAdapter);

        return view;

    }
}