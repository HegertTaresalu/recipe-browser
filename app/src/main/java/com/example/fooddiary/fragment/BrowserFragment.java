package com.example.fooddiary.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddiary.R;
import com.example.fooddiary.RecipeAdapter;
import com.example.fooddiary.ViewModel.BrowserViewModel;

public class BrowserFragment extends Fragment {
    BrowserViewModel browserViewModel;

    private RecipeAdapter recipeAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        browserViewModel = new ViewModelProvider(this).get(BrowserViewModel.class);
        browserViewModel.getRecipeData();
        browserViewModel.getRecipeLiveData()
                .observe(this,recipes -> recipeAdapter.setRecipeList(recipes));


    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_browser,container,false);

        RecyclerView recyclerView = view.findViewById(R.id.recipeRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setHasFixedSize(true);
        recipeAdapter = new RecipeAdapter();
        recyclerView.setAdapter(recipeAdapter);
        return view;



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}