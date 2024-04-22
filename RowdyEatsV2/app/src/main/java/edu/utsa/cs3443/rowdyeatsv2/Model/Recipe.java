package edu.utsa.cs3443.rowdyeatsv2.Model;

import android.content.res.AssetManager;

import java.io.Serializable;
import java.util.ArrayList;

import edu.utsa.cs3443.rowdyeatsv2.R;
import edu.utsa.cs3443.rowdyeatsv2.data.CSVReader;
import edu.utsa.cs3443.rowdyeatsv2.data.Constants;

public class Recipe implements Serializable {

    private static final String DATA_FILE_PATH = "csv/Recipe/recipes.csv";

    public static ArrayList<Recipe> getModels(AssetManager assets) {
        ArrayList<Recipe> list = new ArrayList<>();
        /*
        // add data to array list
        // TODO : make this stuff not hardcoded
        list.add(new Recipe("Pumpkin Mac & Cheese",R.drawable.recipe_pumpkin_mac_n_cheese, R.string.recipe_pumpkin_mac_n_cheese));
        list.add(new Recipe("Ratatouille",R.drawable.recipe_ratatouille, R.string.recipe_ratatouille));
        list.add(new Recipe("Philadelphia Roll", R.drawable.recipe_sushi, R.string.recipe_sushi));
        list.add(new Recipe("Breakfast Sandwich",R.drawable.recipe_bkfst_sandwich, R.string.recipe_bkfst_sandwich));
        list.add(new Recipe("Lemon Garlic Chicken",R.drawable.recipe_garlic_chicken,R.string.recipe_garlic_chicken));
        list.add(new Recipe("Garlic Parmesan Pasta",R.drawable.recipe_parmesan_pasta,R.string.recipe_parmesan_pasta));
         */
        ArrayList<String[]> csvRawData = CSVReader.readStrings(assets,DATA_FILE_PATH);
        for (String[] strings : csvRawData) {
            list.add(new Recipe(strings[0], Constants.DRAWABLES.get(strings[1]),Constants.STRINGS.get(strings[2])));
        }
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
