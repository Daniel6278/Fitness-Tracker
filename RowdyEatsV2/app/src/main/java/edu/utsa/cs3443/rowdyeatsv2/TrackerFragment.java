package edu.utsa.cs3443.rowdyeatsv2;

import android.os.Bundle;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import edu.utsa.cs3443.rowdyeatsv2.data.DatabaseHandler;
import edu.utsa.cs3443.rowdyeatsv2.model.Food;
import edu.utsa.cs3443.rowdyeatsv2.DetailFoodActivity;

public class TrackerFragment extends Fragment {

    private EditText food, calories; // Assuming these are the correct variable names
    private Button submit;
    private DatabaseHandler dba; // Declare the DatabaseHandler

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calorie_tracker, container, false);
        dba = new DatabaseHandler(getActivity()); // Initialize the DatabaseHandler

        // Initialize your views here
        food = view.findViewById(R.id.CaloriesFood);
        calories = view.findViewById(R.id.caloriesNumber);
        submit = view.findViewById(R.id.submitButton);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataTODB(); // Call your method to save data
            }
        });

        return view;
    }

    private void saveDataTODB() {
        // Use 'food' instead of 'foodName' and ensure that this is the correct EditText
        String name = food.getText().toString().trim();
        String cal = calories.getText().toString().trim();

        if (!name.isEmpty() && !cal.isEmpty()) {
            int calInt = Integer.parseInt(cal);
            Food food = new Food();
            food.setFoodName(name);
            food.setCalories(calInt);

            dba.addFood(food); // Now dba is initialized and can be used

            food.setFoodName(""); // Reset the EditTexts
            calories.setText("");

            Toast.makeText(getContext(), "Food added!", Toast.LENGTH_LONG).show();

            // Start the DetailFoodActivity, make sure this activity exists in your project
            Intent intent = new Intent(getActivity(), DetailFoodActivity.class);
            startActivity(intent);

        } else {
            Toast.makeText(getContext(), "Please enter some value", Toast.LENGTH_LONG).show();
        }
    }
}