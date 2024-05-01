package edu.utsa.cs3443.rowdyeatsv2.Model;

import android.content.Context;

import edu.utsa.cs3443.rowdyeatsv2.data.DatabaseHandler;
import edu.utsa.cs3443.rowdyeatsv2.Model.FoodRecord;
import java.util.ArrayList;



public class FoodActivityModel {

    private DatabaseHandler dbHandler;

    public FoodActivityModel(Context context) {
        this.dbHandler = new DatabaseHandler(context);
    }

    public ArrayList<FoodRecord> getAllFoods() {
        return dbHandler.getAllFood();
    }

    public void closeDatabase() {
        dbHandler.close();
    }
}
