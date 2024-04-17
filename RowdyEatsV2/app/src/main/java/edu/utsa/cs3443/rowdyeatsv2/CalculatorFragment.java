package edu.utsa.cs3443.rowdyeatsv2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class CalculatorFragment extends Fragment {

    private EditText feetInput, inchesInput, weightInput, ageInput;
    private RadioGroup genderGroup, activityLevelGroup;
    private Button calculateButton;
    private TextView resultText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calorie_calculator, container, false);

        // Initialize inputs
        feetInput = view.findViewById(R.id.feetInput);
        inchesInput = view.findViewById(R.id.inchesInput);
        weightInput = view.findViewById(R.id.weightInput);
        ageInput = view.findViewById(R.id.ageInput);
        genderGroup = view.findViewById(R.id.genderGroup);
        activityLevelGroup = view.findViewById(R.id.activityLevelGroup);
        calculateButton = view.findViewById(R.id.calculateButton);
        resultText = view.findViewById(R.id.resultText);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int feet = Integer.parseInt(feetInput.getText().toString());
                    int inches = Integer.parseInt(inchesInput.getText().toString());
                    int weight = Integer.parseInt(weightInput.getText().toString());
                    int age = Integer.parseInt(ageInput.getText().toString());
                    int genderId = genderGroup.getCheckedRadioButtonId();
                    int activityLevelId = activityLevelGroup.getCheckedRadioButtonId();

                    String gender = (genderId == R.id.maleButton) ? "male" : "female";
                    String activityLevel = getActivityLevel(activityLevelId);

                    double calories = calculateMaintenanceCalories(feet, inches, weight, age, gender, activityLevel);
                    resultText.setText("Daily maintenance calories: " + String.format("%.2f", calories));
                } catch (NumberFormatException e) {
                    Toast.makeText(getActivity(), "Please enter valid numbers", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Error calculating calories", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private String getActivityLevel(int id) {
        switch (id) {
            case R.id.noExerciseButton: return "little or no exercise";
            case R.id.lightlyActiveButton: return "lightly active";
            case R.id.moderatelyActiveButton: return "moderately active";
            case R.id.veryActiveButton: return "very active";
            case R.id.extraActiveButton: return "extra active";
            default: return "";
        }
    }

    public static double calculateMaintenanceCalories(int feet, int inches, int weightPounds, int age, String gender, String activityLevel) {
        double heightInCm = ((feet * 12) + inches) * 2.54; // Convert height from feet and inches to cm
        double weightInKg = weightPounds / 2.20462; // Convert weight from pounds to kilograms

        // Calculate Basal Metabolic Rate (BMR) using Mifflin-St Jeor Equation
        double bmr = (gender.equalsIgnoreCase("male")) ?
                (10 * weightInKg + 6.25 * heightInCm - 5 * age + 5) :
                (10 * weightInKg + 6.25 * heightInCm - 5 * age - 161);

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
}
