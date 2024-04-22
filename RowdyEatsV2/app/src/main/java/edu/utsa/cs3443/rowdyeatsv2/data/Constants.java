package edu.utsa.cs3443.rowdyeatsv2.data;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import edu.utsa.cs3443.rowdyeatsv2.R;

public class Constants {
    public static final Map<String,Integer> DRAWABLES;
    public static final Map<String,Integer> STRINGS;
    static {
        Map<String, Integer> aMap = new HashMap<>();
        aMap.put("chickfilalogo",R.drawable.chickfilalogo);
        aMap.put("sushiclogo",R.drawable.sushiclogo);
        aMap.put("subwaylogo",R.drawable.subwaylogo);
        aMap.put("utsabirdlogo_transparent",R.drawable.utsabirdlogo_transparent);
        DRAWABLES = Collections.unmodifiableMap(aMap);

        Map<String, Integer> bMap = new HashMap<>();
        bMap.put("recipe_ratatouille",R.string.recipe_ratatouille);
        bMap.put("recipe_pumpkin_mac_n_cheese",R.string.recipe_pumpkin_mac_n_cheese);
        STRINGS = Collections.unmodifiableMap(aMap);
    }
    public static final String DATABASE_NAME = "caloriesDb";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "food_table";
    public static final String FOOD_NAME = "food";
    public static final String CALORIES = "cal";
    public static final String DATE_ADDED = "date";
    public static final String KEY_ID = "_id";

}
