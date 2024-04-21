package edu.utsa.cs3443.rowdyeatsv2.Model;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

public class FoodRecord implements Serializable {

    private String foodName;
    private Date recordDate;
    private int calories, dbKey;
    private static final long serialVersionUID = 10L;

    public FoodRecord(String foodName, int calories) {
        this.foodName = foodName;
        this.calories = calories;
    }

    public FoodRecord(String foodName, int calories, int dbKey, Date recordDate) {
        this.foodName = foodName;
        this.calories = calories;
        this.dbKey = dbKey;
        this.recordDate = recordDate;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Date getRecordDate() {
        return recordDate;
    }
    public String getRecordDateStr() {
        DateFormat dateFormat = DateFormat.getDateInstance();
        return dateFormat.format(getRecordDate());
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getDbKey() {
        return dbKey;
    }
    public void setDbKey(int newKey) {
        dbKey = newKey;
    }

    public static long getSerialVersionID() {
        return serialVersionUID;
    }
}
