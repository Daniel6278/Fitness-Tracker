package edu.utsa.cs3443.rowdyeatsv2.Model;

import android.content.res.AssetManager;

import java.io.Serializable;
import java.util.ArrayList;

import edu.utsa.cs3443.rowdyeatsv2.R;
import edu.utsa.cs3443.rowdyeatsv2.data.CSVReader;
import edu.utsa.cs3443.rowdyeatsv2.data.Constants;

public class FoodPreset implements Serializable {

    private static final int DEFAULT_IMAGE_ID = R.drawable.icon_food_faded;

    private static final String DATA_FOLDER_PATH = "csv/FoodPreset";

    private String foodName;
    private Nutrition nutrition;
    private int calories;

    private int imgId;

    public static ArrayList<FoodPreset> getModels(AssetManager assets,Restaurant parent) {
        ArrayList<FoodPreset> list = new ArrayList<>();
        /*
        switch (parentCategoryName) {
            // add data to array list
            // TODO : make this stuff not hardcoded
            case "Chick-fil-A":
                list.add(new FoodPreset("Chicken Sandwich", 450, R.drawable.chickfilalogo, new Nutrition(450,50,60,70)));
                list.add(new FoodPreset("Spicy Chicken Sandwich", 475, R.drawable.chickfilalogo, new Nutrition(475,55,65,75)));
                list.add(new FoodPreset("Deluxe Chicken Sandwich", 500, R.drawable.chickfilalogo));
                list.add(new FoodPreset("Spicy Deluxe Chicken Sandwich", 500, R.drawable.chickfilalogo));
                list.add(new FoodPreset("Potato Fries (M)", 200, R.drawable.chickfilalogo));
                break;
            case "SushiC":
                list.add(new FoodPreset("Fish", 50, R.drawable.recipe_sushi,new Nutrition(20,1,1,1)));
                break;
            case "Subway":
                list.add(new FoodPreset("Subway Club (6\")", 750, R.drawable.subwaylogo,new Nutrition(750,30,120,45)));
                break;
        }
        */
        ArrayList<String[]> csvRawData = CSVReader.readStrings(assets,DATA_FOLDER_PATH + "/" + parent.getFoodsFileName() + ".csv");
        for (String[] strings : csvRawData) {
            String name = strings[0];
            int calories = Integer.parseInt(strings[1]);
            if (strings.length < 3) {
                // when no macros provided in CSV
                list.add(new FoodPreset(name,calories));
                continue;
            }
            // when macros are provided in CSV
            double fat = Double.parseDouble(strings[2]);
            double carbs = Double.parseDouble(strings[3]);
            double protein = Double.parseDouble(strings[4]);
            if (strings.length < 6) {
                // when no image provided in CSV
                list.add(new FoodPreset(name,calories,new Nutrition(calories,fat,carbs,protein)));
                continue;
            }
            // when image is provided in CSV
            list.add(new FoodPreset(name,calories,new Nutrition(calories,fat,carbs,protein),Constants.DRAWABLES.get(strings[5])));
        }
        return list;
    }

    public FoodPreset(String foodName, int calories) {
        this.foodName = foodName;
        this.calories = calories;
        this.imgId = DEFAULT_IMAGE_ID;
    }

    public FoodPreset(String foodName, int calories, Nutrition nutrition) {
        this.foodName = foodName;
        this.calories = calories;
        this.imgId = DEFAULT_IMAGE_ID;
        this.nutrition = nutrition;
    }

    /*
    public FoodPreset(String foodName, int calories, int imgId) {
        this.foodName = foodName;
        this.calories = calories;
        this.imgId = imgId;
    }
     */

    public FoodPreset(String foodName, int calories, Nutrition nutrition, int imgId) {
        this.foodName = foodName;
        this.calories = calories;
        this.imgId = imgId;
        this.nutrition = nutrition;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getNutritionText() { return hasNutritionBreakdown() ? this.nutrition.toString() : "No Nutrition Facts"; }

    public boolean hasNutritionBreakdown() {
        return this.nutrition != null;
    }
}
