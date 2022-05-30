package com.example.fooddiary.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.TextViewKt;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fooddiary.R;
import com.squareup.picasso.Picasso;

public class recipe_fragment extends Fragment {
/*

  private final String title;
    private final String image;
    private final String dishType;
    private final String sourceUrl;
    private final int readyIn;
    private final Boolean dairyFree;
    private final Boolean vegetarian;
    private final Boolean vegan;

 */


    TextView title;
    ImageView image;
    TextView dishType;
    TextView sourceUrl;
    TextView prepTime;
    TextView isDairyFree;
    TextView isVegetarian;
    TextView isVegan;
    Bundle args;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    /*
     args = new Bundle();
            args.putString("recipe_title",recipe.getTitle());
            args.putString("recipe_type", recipe.getDishType());
            args.putString("recipe_image", recipe.getImage());
            args.putString("recipe_src_url", recipe.getSourceUrl());
            args.putBoolean("isDairy",recipe.getDairyFree());
            args.putBoolean("isVegan",recipe.getVegan());
            args.putBoolean("isVegetarian",recipe.getVegetarian());
     */


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipe, container, false);
        dishType = view.findViewById(R.id.recipeDishTypeTxt);
        title = view.findViewById(R.id.recipeTitleTxt);
        image = view.findViewById(R.id.recipeImg);
        sourceUrl = view.findViewById(R.id.recipeSrcUrl);
        prepTime = view.findViewById(R.id.recipePrepTimeTxt);
        isDairyFree = view.findViewById(R.id.recipeIsDairyFree);
        isVegetarian = view.findViewById(R.id.isVegetarian);
        isVegan = view.findViewById(R.id.isVeganTxt);


        args = getArguments();
        dishType.setText(args.getString("recipe_type"));
        title.setText(args.getString("recipe_title"));
        sourceUrl.setText(args.getString(""));
        //TODO show image
       // Picasso.get().load(args.getString("recipe_src_url")).into(image);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}