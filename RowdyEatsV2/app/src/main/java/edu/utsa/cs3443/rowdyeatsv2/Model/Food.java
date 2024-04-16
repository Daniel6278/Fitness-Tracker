package edu.utsa.cs3443.rowdyeatsv2.Model;

import java.io.Serializable;

public class Food implements Serializable {

    private String foodName, recordDate;
    private int calories, foodId;
    private static final long serialVersionUID = 10L;


    public Food() {
    }

    public Food(String foodName, int calories) {
        this.foodName = foodName;
        this.calories = calories;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public static long getSerialVersionID() {
        return serialVersionUID;
    }
}
