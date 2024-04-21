package edu.utsa.cs3443.rowdyeatsv2.Model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Nutrition implements Serializable {
    private final double calories;
    private final double fat;
    private final double carbs;
    private final double protein;

    public Nutrition(double calories, double fat, double carbs, double protein) {
        this.calories = calories;
        this.fat = fat;
        this.carbs = carbs;
        this.protein = protein;
    }

    @NonNull
    @Override
    public String toString() {
        return "Calories: " + calories + "\nFat: " + fat + "g\nCarbs: " + carbs + "g\nProtein: " + protein + "g";
    }
}
