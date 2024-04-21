package edu.utsa.cs3443.rowdyeatsv2.Model;

import java.io.Serializable;
import java.util.ArrayList;

import edu.utsa.cs3443.rowdyeatsv2.R;

public class FoodPreset implements Serializable {

    private String foodName;
    private Nutrition nutrition;
    private int calories;

    private int imgId;

    public static ArrayList<FoodPreset> getModels(String parentCategoryName) {
        ArrayList<FoodPreset> list = new ArrayList<>();

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
        return list;
    }

    public FoodPreset(String foodName, int calories, int imgId) {
        this.foodName = foodName;
        this.calories = calories;
        this.imgId = imgId;
    }

    public FoodPreset(String foodName, int calories, int imgId, Nutrition nutrition) {
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
