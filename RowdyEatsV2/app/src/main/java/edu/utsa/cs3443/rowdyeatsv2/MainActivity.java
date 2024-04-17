package edu.utsa.cs3443.rowdyeatsv2;

import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import android.view.View;
import android.widget.Toast;

import edu.utsa.cs3443.rowdyeatsv2.data.DatabaseHandler;
import edu.utsa.cs3443.rowdyeatsv2.model.FoodRecord;

public class MainActivity extends AppCompatActivity
        implements NavigationBarView
        .OnItemSelectedListener {

    private DatabaseHandler dba;
    public DatabaseHandler getDba() {
        return dba;
    }

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        bottomNavigationView
                = findViewById(R.id.bottomNavigationView);

        bottomNavigationView
                .setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.nav_btn_home);

        dba = new DatabaseHandler(this);
    }
    CafeFragment2 cafeFragment = new CafeFragment2();
    CalculatorFragment calculatorFragment = new CalculatorFragment();
    HomeFragment homeFragment = new HomeFragment();
    RecipesFragment recipesFragment = new RecipesFragment();
    TrackerFragment trackerFragment = new TrackerFragment();

    @Override
    public boolean
    onNavigationItemSelected(@NonNull MenuItem item)
    {

        switch (item.getItemId()) {
            case R.id.nav_btn_home:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, homeFragment)
                        .commit();
                return true;

            case R.id.nav_btn_tracker:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, trackerFragment)
                        .commit();
                return true;

            case R.id.nav_btn_cafe:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, cafeFragment)
                        .commit();
                return true;

            case R.id.nav_btn_recipes:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, recipesFragment)
                        .commit();
                return true;

            case R.id.nav_btn_calculator:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, calculatorFragment)
                        .commit();
                return true;
        }
        return false;
    }

    private void saveDataTODB(String mealName, String calories) {

        mealName = mealName.trim();
        calories = calories.trim();
        Log.println(Log.INFO,"saveDataTODB:mealName",mealName);
        Log.println(Log.INFO,"saveDataTODB:calories",calories);

        if (mealName.isEmpty() || calories.isEmpty()) {
            Toast.makeText(this, "Please fill in all of the fields", Toast.LENGTH_LONG).show();
            return;
        }

        int nCalories;
        try {
            nCalories = Integer.parseUnsignedInt(calories);
        } catch (NumberFormatException e){
            Toast.makeText(this, "Can’t parse calories", Toast.LENGTH_LONG).show();
            return;
        }

        FoodRecord newFood = new FoodRecord();
        newFood.setFoodName(mealName);
        newFood.setCalories(nCalories);

        dba.addFood(newFood);

        Toast.makeText(this, "Meal logged!", Toast.LENGTH_LONG).show();
    }

    public void showTrackerDialog(TrackerFragment.RefreshListener r) {
        showTrackerDialog(r,"","");
    }
    public void showTrackerDialog(TrackerFragment.RefreshListener r,int DBID) {
        showTrackerDialog(r);
    }

    public void showTrackerDialog(TrackerFragment.RefreshListener r,String foodInputContents,String caloriesInputContents) {
        // Create an alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Meal Log Entry");

        // set the custom layout
        final View customLayout = getLayoutInflater().inflate(R.layout.dialog_tracker_prompt, null);
        builder.setView(customLayout);
        EditText food = customLayout.findViewById(R.id.caloriesFood);
        food.setText(foodInputContents);
        EditText calories = customLayout.findViewById(R.id.caloriesNumber);
        calories.setText(caloriesInputContents);

        // add a button
        builder.setPositiveButton("Submit", (dialog, which) -> {
            // sends data from the AlertDialog to the Activity
            saveDataTODB(food.getText().toString(),calories.getText().toString());
            r.onRefresh(); // refreshes log display
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> {
            dialog.dismiss(); // dismisses the dialog
        });

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}