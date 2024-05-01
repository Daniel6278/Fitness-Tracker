package edu.utsa.cs3443.rowdyeatsv2.Model;

import edu.utsa.cs3443.rowdyeatsv2.R;

public class CalculatorModel {

    public static double calculateMaintenanceCalories(int feet, int inches, int weightPounds, int age, String gender, String activityLevel) {
        double heightInCm = ((feet * 12) + inches) * 2.54; // Convert height from feet and inches to cm
        double weightInKg = weightPounds / 2.20462; // Convert weight from pounds to kilograms

        // Harris-Benedict Equation
        double bmr = (gender.equalsIgnoreCase("male")) ?
                66 + (13.7 * weightInKg) + (5 * heightInCm) - (6.8 * age) :
                655 + (9.6 * weightInKg) + (1.8 * heightInCm) - (4.7 * age);

        // Adjust BMR based on activity level
        switch (activityLevel.toLowerCase()) {
            case "little or no exercise": return bmr * 1.2;
            case "lightly active": return bmr * 1.375;
            case "moderately active": return bmr * 1.55;
            case "very active": return bmr * 1.725;
            case "extra active": return bmr * 1.9;
            default: return -1; // Invalid activity level
        }
    }

    public String getActivityLevel(int id) {
        switch (id) {
            case R.id.noExerciseButton: return "little or no exercise";
            case R.id.lightlyActiveButton: return "lightly active";
            case R.id.moderatelyActiveButton: return "moderately active";
            case R.id.veryActiveButton: return "very active";
            case R.id.extraActiveButton: return "extra active";
            default: return "";
        }
    }
}
