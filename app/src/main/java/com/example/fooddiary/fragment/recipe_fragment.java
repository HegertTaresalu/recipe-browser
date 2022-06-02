package com.example.fooddiary.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.TextViewKt;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fooddiary.R;
import com.example.fooddiary.ViewModel.BrowserViewModel;
import com.example.fooddiary.ViewModel.LoginViewModel;
//import com.squareup.picasso.Picasso;

public class recipe_fragment extends Fragment {

    TextView title;
    ImageView image;
    TextView dishType;
    TextView sourceUrl;
    TextView prepTime;
    TextView isDairyFree;
    TextView isVegetarian;
    TextView isVegan;
    Bundle args;
    Button button;
    Button saveDataBtn;
    NavController navController;
    BrowserViewModel browserViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipe, container, false);
        saveDataBtn = view.findViewById(R.id.bookMarkBtn);
        button = view.findViewById(R.id.calendarBtn);
        dishType = view.findViewById(R.id.recipeDishTypeTxt);
        title = view.findViewById(R.id.recipeTitleTxt);
        image = view.findViewById(R.id.recipeImg);
        sourceUrl = view.findViewById(R.id.recipeSrcUrl);
        prepTime = view.findViewById(R.id.recipePrepTimeTxt);
        isDairyFree = view.findViewById(R.id.recipeIsDairyFree);
        isVegetarian = view.findViewById(R.id.isVegetarian);
        isVegan = view.findViewById(R.id.isVeganTxt);
        browserViewModel = new ViewModelProvider(this).get(BrowserViewModel.class);

        args = getArguments();
        dishType.setText(args.getString("recipe_type"));
        title.setText(args.getString("recipe_title"));
        sourceUrl.setText(args.getString("recipe_src_url"));
        prepTime.setText(String.valueOf(args.getInt("prep_time")) + " min");
        isDairyFree.setText("Does contain dairy: " + String.valueOf(args.getBoolean("isDairy")));
        isVegetarian.setText("Is vegetarian: " + String.valueOf(args.getBoolean("isVegetarian")));
        isVegan.setText("Is vegan: " + String.valueOf(args.getBoolean("isVegan")));

        //TODO show image
       // Picasso.get().load(args.getString("recipe_src_url")).into(image);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        button.setOnClickListener(view1 -> {
            navController.navigate(R.id.action_recipe_fragment_to_calendarFragment,args);

        });

        saveDataBtn.setOnClickListener(view1 -> {
            Runnable runnable = () ->  browserViewModel.getData();

            Thread bgThread = new Thread(runnable);
            bgThread.start();

            Runnable data = () -> browserViewModel.addData(args);

            Thread addData = new Thread(data);
            addData.start();



        });

    }
}