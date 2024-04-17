package edu.utsa.cs3443.rowdyeatsv2.model;

import java.util.ArrayList;

import edu.utsa.cs3443.rowdyeatsv2.R;

public class FoodPreset {

    private String foodName;
    private int calories;

    private int imgId;

    public static ArrayList<FoodPreset> getModels(String parentCategoryName) {
        ArrayList<FoodPreset> list = new ArrayList<>();

        switch (parentCategoryName) {
            // add data to array list
            // TODO : make this stuff not hardcoded
            case "Chick-Fil-A":
                list.add(new FoodPreset("Chicken Sandwich", 450, R.drawable.chickfilalogo));
                list.add(new FoodPreset("Spicy Chicken Sandwich", 450, R.drawable.chickfilalogo));
                list.add(new FoodPreset("Deluxe Chicken Sandwich", 500, R.drawable.chickfilalogo));
                list.add(new FoodPreset("Spixy Deluxe Chicken Sandwich", 500, R.drawable.chickfilalogo));
                break;
            case "Suchi-C":
                list.add(new FoodPreset("Fish", 50, R.drawable.recipe_sushi));
                break;
            case "Subway":
                list.add(new FoodPreset("Subway Club (6\")", 750, R.drawable.subwaylogo));
                break;
        }
        return list;
    }
    public FoodPreset() {
    }

    public FoodPreset(String foodName, int calories, int imgId) {
        this.foodName = foodName;
        this.calories = calories;
        this.imgId = imgId;
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
}
