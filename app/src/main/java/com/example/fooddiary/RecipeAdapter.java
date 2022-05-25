package com.example.fooddiary;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddiary.models.Recipe;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    private ArrayList<Recipe> recipeList;

    public RecipeAdapter(){
        this.recipeList = new ArrayList<>();
    }



    @NonNull
    @Override
    public RecipeAdapter.RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_recipe,parent,false);

        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.RecipeViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        Log.i("gamng",recipe.getTitle());
        Log.i("gamgn",recipe.getDishType());
        holder.title.setText(recipe.getTitle());
        holder.dishType.setText(recipe.getDishType());
        holder.prepTime.setText(Integer.toString(recipe.getReadyIn()));

    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public void setRecipeList(final ArrayList<Recipe> recipeList){this.recipeList = recipeList; notifyDataSetChanged();}


    static class RecipeViewHolder extends RecyclerView.ViewHolder{
        private RecipeViewHolder(@NonNull View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.titleTxt);
            dishType = itemView.findViewById(R.id.dishTypeTxt);
            prepTime = itemView.findViewById(R.id.preptimeTxt);



        }

        private final TextView title;
        private final TextView dishType;
        private final TextView prepTime;

    }


}
