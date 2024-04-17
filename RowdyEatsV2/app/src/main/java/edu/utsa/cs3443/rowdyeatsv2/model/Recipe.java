package edu.utsa.cs3443.rowdyeatsv2.model;

import java.util.ArrayList;

import edu.utsa.cs3443.rowdyeatsv2.R;

public class Recipe {

    public static ArrayList<Recipe> getModels() {
        ArrayList<Recipe> list = new ArrayList<>();
        // add data to array list
        // TODO : make this stuff not hardcoded
        list.add(new Recipe("Sushi", R.drawable.recipe_sushi, R.string.recipe_sushi));
        list.add(new Recipe("Breakfast Sandwich",R.drawable.recipe_bkfst_sandwich, R.string.recipe_bkfst_sandwich));
        list.add(new Recipe("Pumpkin Mac & Cheese",R.drawable.recipe_pumpkin_mac_n_cheese, R.string.recipe_pumpkin_mac_n_cheese));
        list.add(new Recipe("Ratatouille",R.drawable.recipe_ratatouille, R.string.recipe_ratatouille));
        return list;
    }
    private String title;
    private int imgId;
    private int recipeBodyId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public int getRecipeBodyId() {
        return recipeBodyId;
    }

    public void setRecipeBodyId(int recipeBodyId) {
        this.recipeBodyId = recipeBodyId;
    }

    public Recipe(String title, int imgId, int recipeBodyId) {
        this.title = title;
        this.imgId = imgId;
        this.recipeBodyId = recipeBodyId;
    }
}
