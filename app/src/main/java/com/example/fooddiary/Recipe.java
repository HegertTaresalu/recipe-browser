package com.example.fooddiary;

public class Recipe {
    private final int id;
    private final String title;
    //private final String image;
    private final String dishType;
    private final String sourceUrl;
    private final int readyIn;
    private final Boolean dairyFree;
    private final Boolean vegetarian;
    private final Boolean vegan;

    public Recipe(int id, String title, String dishType, String sourceUrl, int readyIn, Boolean dairyFree, Boolean vegetarian, Boolean vegan) {
        this.id = id;
        this.title = title;
        //this.image = image;
        this.dishType = dishType;
        this.sourceUrl = sourceUrl;
        this.readyIn = readyIn;
        this.dairyFree = dairyFree;
        this.vegetarian = vegetarian;
        this.vegan = vegan;
    }


    public int getId() { return id; }

    public String getTitle() { return title; }

    //public String getImage() { return image; }

    public String getDishType() { return dishType; }

    public String getSourceUrl() { return sourceUrl; }

    public int getReadyIn() { return readyIn; }

    public Boolean getDairyFree() { return dairyFree; }

    public Boolean getVegetarian() { return vegetarian; }

    public Boolean getVegan() { return vegan; }
}

