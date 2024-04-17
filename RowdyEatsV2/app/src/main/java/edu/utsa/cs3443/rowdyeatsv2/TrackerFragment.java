package edu.utsa.cs3443.rowdyeatsv2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import edu.utsa.cs3443.rowdyeatsv2.data.DatabaseHandler;
import edu.utsa.cs3443.rowdyeatsv2.model.FoodRecord;

public class TrackerFragment extends Fragment {

    private EditText food, calories;
    private Button submit;
    private DatabaseHandler dba;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calorie_tracker, container, false);
        dba = new DatabaseHandler(getActivity());

        food = view.findViewById(R.id.CaloriesFood);
        calories = view.findViewById(R.id.caloriesNumber);
        submit = view.findViewById(R.id.submitButton);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataTODB();
            }
        });

        return view;
    }

    private void saveDataTODB() {
        String name = food.getText().toString().trim();
        String cal = calories.getText().toString().trim();

        if (!name.isEmpty() && !cal.isEmpty()) {
            int calInt = Integer.parseInt(cal);
            FoodRecord newFood = new FoodRecord();
            newFood.setFoodName(name);
            newFood.setCalories(calInt);

            dba.addFood(newFood);

            Toast.makeText(getContext(), "Food added!", Toast.LENGTH_LONG).show();

            // Navigate back to CafeFragment
           /* FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            CafeFragment cafeFragment = new CafeFragment();
            fragmentTransaction.switch(R.id.nav_btn_cafe, cafeFragment);
            fragmentTransaction.commit();*/
        } else {
            Toast.makeText(getContext(), "Please enter some value", Toast.LENGTH_LONG).show();
        }
    }
}
