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
import edu.utsa.cs3443.rowdyeatsv2.Model.CalculatorModel;

public class CalculatorFragment extends Fragment {

    private EditText feetInput, inchesInput, weightInput, ageInput;
    private RadioGroup genderGroup, activityLevelGroup;
    private Button calculateButton;
    private TextView resultText;
    private CalculatorModel model = new CalculatorModel();

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
                    String activityLevel = model.getActivityLevel(activityLevelId);

                    double calories = CalculatorModel.calculateMaintenanceCalories(feet, inches, weight, age, gender, activityLevel);
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
}
