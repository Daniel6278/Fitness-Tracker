package edu.utsa.cs3443.rowdyeatsv2;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import edu.utsa.cs3443.rowdyeatsv2.Model.FoodActivityModel;
import edu.utsa.cs3443.rowdyeatsv2.Model.FoodRecord;
import edu.utsa.cs3443.rowdyeatsv2.adapters.CustomDataAdapter;
import java.util.ArrayList;

public class DetailFoodActivity extends AppCompatActivity {

    private FoodActivityModel foodModel;
    private ArrayList<FoodRecord> foodArrayList = new ArrayList<>();
    private CustomDataAdapter foodAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tracker);

        listView = findViewById(R.id.listView);
        foodModel = new FoodActivityModel(getApplicationContext());

        refreshData();
    }

    private void refreshData() {
        foodArrayList.clear();
        ArrayList<FoodRecord> foodsFromDB = foodModel.getAllFoods();

        for (FoodRecord food : foodsFromDB) {
            // Using the constructor that includes all attributes
            foodArrayList.add(new FoodRecord(food.getFoodName(), food.getCalories(), food.getDbKey(), food.getRecordDate()));
        }
        foodModel.closeDatabase();

        // Setup Adapter
        foodAdapter = new CustomDataAdapter(this, R.layout.list_row_recorded_food, foodArrayList);
        listView.setAdapter(foodAdapter);
        foodAdapter.setNotifyOnChange(true);
    }
}
