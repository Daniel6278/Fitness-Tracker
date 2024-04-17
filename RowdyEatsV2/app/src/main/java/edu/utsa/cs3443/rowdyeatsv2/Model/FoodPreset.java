package edu.utsa.cs3443.rowdyeatsv2.model;

import java.util.ArrayList;

import edu.utsa.cs3443.rowdyeatsv2.R;

public class FoodPreset {

    private String foodName;
    private int calories;

    private int imgId;

    public static ArrayList<FoodPreset> getModels() {
        ArrayList<FoodPreset> list = new ArrayList<>();
        // add data to array list
        list.add(new FoodPreset("Chick-Fil-A", 100, R.drawable.chickfilalogo));
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
