package edu.utsa.cs3443.rowdyeatsv2.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;
import edu.utsa.cs3443.rowdyeatsv2.Model.FoodRecord;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DatabaseHandler extends SQLiteOpenHelper {
    private final ArrayList<FoodRecord> foodList = new ArrayList<>();

    public DatabaseHandler(@Nullable Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + "("
                + Constants.KEY_ID + " INTEGER PRIMARY KEY, "
                + Constants.FOOD_NAME + " TEXT, "
                + Constants.CALORIES + " INT, "
                + Constants.DATE_ADDED + " LONG );";

        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);

        onCreate(db);
    }

    //add food method
    public void addFood(FoodRecord food) {
        SQLiteDatabase dba = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.FOOD_NAME, food.getFoodName());
        values.put(Constants.CALORIES, food.getCalories());
        values.put(Constants.DATE_ADDED, System.currentTimeMillis());

        dba.insert(Constants.TABLE_NAME, null, values);
        Log.d("Added Food Item", "YES");
        dba.close();
    }

    public ArrayList<FoodRecord> getAllFood() {
        foodList.clear();
        SQLiteDatabase dba = this.getReadableDatabase();
        Cursor cursor = dba.query(Constants.TABLE_NAME,
                new String[]{Constants.KEY_ID, Constants.FOOD_NAME, Constants.CALORIES, Constants.DATE_ADDED},
                null, null, null, null, Constants.DATE_ADDED + " DESC ");

        if (cursor.moveToFirst()) {
            do {
                FoodRecord food = new FoodRecord();

                int foodNameIndex = cursor.getColumnIndex(Constants.FOOD_NAME);
                int caloriesIndex = cursor.getColumnIndex(Constants.CALORIES);
                int idIndex = cursor.getColumnIndex(Constants.KEY_ID);
                int dateAddedIndex = cursor.getColumnIndex(Constants.DATE_ADDED);

                // Check if any of the column indices are -1
                if (foodNameIndex != -1 && caloriesIndex != -1 && idIndex != -1 && dateAddedIndex != -1) {
                    food.setFoodName(cursor.getString(foodNameIndex));
                    food.setCalories(cursor.getInt(caloriesIndex));
                    food.setFoodId(cursor.getInt(idIndex));

                    DateFormat dateFormat = DateFormat.getDateInstance();
                    String date = dateFormat.format(new Date(cursor.getLong(dateAddedIndex)).getTime());
                    food.setRecordDate(date);

                    foodList.add(food);
                } else {
                    // Handle the error or skip the row if the column index is invalid
                    Log.e("DatabaseHandler", "Column Index not found");
                }

            } while (cursor.moveToNext());
        }
        cursor.close();
        dba.close();
        return foodList;
    }

}
