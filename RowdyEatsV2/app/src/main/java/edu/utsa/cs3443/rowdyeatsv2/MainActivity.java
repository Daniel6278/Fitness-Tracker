package edu.utsa.cs3443.rowdyeatsv2;

import android.os.Bundle;

import android.view.MenuItem;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import edu.utsa.cs3443.rowdyeatsv2.data.DatabaseHandler;
import edu.utsa.cs3443.rowdyeatsv2.Model.FoodRecord;

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
    CafeFragment cafeFragment = new CafeFragment();
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
        saveDataTODB(mealName,calories,null);
    }

    private void saveDataTODB(String mealName, String calories, Integer dbKey) {

        mealName = mealName.trim();
        calories = calories.trim();
        //Log.println(Log.INFO,"saveDataTODB:mealName",mealName);
        //Log.println(Log.INFO,"saveDataTODB:calories",calories);

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

        FoodRecord newFood = new FoodRecord(mealName,nCalories);

        if (dbKey == null) {
            dba.addFood(newFood);
        } else {
            newFood.setDbKey(dbKey);
            dba.updateFood(newFood);
        }

        Toast.makeText(this, "Meal logged!", Toast.LENGTH_LONG).show();
    }

    public void deleteFoodRecord(FoodRecord f) {
        dba.deleteFood(f);
        Toast.makeText(this, "Meal deleted.", Toast.LENGTH_LONG).show();
    }

    public void showTrackerDialog(TrackerFragment.RefreshListener r) {
        showTrackerDialog(r,null);
    }

    public void showTrackerDialog(TrackerFragment.RefreshListener r,FoodRecord foodRecordToEditOrCreate) {
        final boolean noFieldsPreFill = foodRecordToEditOrCreate == null;
        final boolean isCreating = noFieldsPreFill || foodRecordToEditOrCreate.getRecordDate() == null;
        // Create an alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(isCreating ? "Create Meal Log Entry" : "Edit Meal Log Entry");

        // set the custom layout
        final View customLayout = getLayoutInflater().inflate(R.layout.dialog_tracker_prompt, null);
        builder.setView(customLayout);
        EditText food = customLayout.findViewById(R.id.caloriesFood);
        EditText calories = customLayout.findViewById(R.id.caloriesNumber);
        TextView editingMsg = customLayout.findViewById(R.id.is_edit_note);
        if (!isCreating) {
            editingMsg.setText(String.format("You are editing an existing meal log entry from %s.", foodRecordToEditOrCreate.getRecordDateStr()));
        } else {
            editingMsg.setVisibility(View.GONE);
        }
        if (!noFieldsPreFill) {
            food.setText(foodRecordToEditOrCreate.getFoodName());
            calories.setText(Integer.toString(foodRecordToEditOrCreate.getCalories()));
        }

        // add a button
        builder.setPositiveButton("Submit", (dialog, which) -> {
            String mealTitleBoxContents = food.getText().toString();
            String caloriesBoxContents = calories.getText().toString();
            // sends data from the AlertDialog to the Activity
            if (isCreating) {
                saveDataTODB(mealTitleBoxContents,caloriesBoxContents);
            } else {
                saveDataTODB(mealTitleBoxContents,caloriesBoxContents,foodRecordToEditOrCreate.getDbKey());
            }
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