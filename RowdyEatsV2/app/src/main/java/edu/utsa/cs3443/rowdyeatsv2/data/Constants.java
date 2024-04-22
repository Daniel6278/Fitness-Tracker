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

        // fooditems

        // categories
        aMap.put("sushiclogo",R.drawable.sushiclogo);
        aMap.put("restaurant_logo_chickfila",R.drawable.restaurant_logo_chickfila);
        aMap.put("restaurant_logo_subway",R.drawable.restaurant_logo_subway);
        aMap.put("utsabirdlogo_transparent",R.drawable.utsabirdlogo_transparent);
        aMap.put("app_logo_transparent",R.drawable.app_logo_transparent);

        // recipes
        aMap.put("recipe_pumpkin_mac_n_cheese",R.drawable.recipe_pumpkin_mac_n_cheese);
        aMap.put("recipe_ratatouille",R.drawable.recipe_ratatouille);
        aMap.put("recipe_parmesan_pasta",R.drawable.recipe_parmesan_pasta);
        aMap.put("recipe_sushi",R.drawable.recipe_sushi);
        aMap.put("recipe_garlic_chicken",R.drawable.recipe_garlic_chicken);
        aMap.put("recipe_bkfst_sandwich",R.drawable.recipe_bkfst_sandwich);

        DRAWABLES = Collections.unmodifiableMap(aMap);

        //

        Map<String, Integer> bMap = new HashMap<>();

        bMap.put("recipe_pumpkin_mac_n_cheese",R.string.recipe_pumpkin_mac_n_cheese);
        bMap.put("recipe_ratatouille",R.string.recipe_ratatouille);
        bMap.put("recipe_parmesan_pasta",R.string.recipe_parmesan_pasta);
        bMap.put("recipe_sushi",R.string.recipe_sushi);
        bMap.put("recipe_garlic_chicken",R.string.recipe_garlic_chicken);
        bMap.put("recipe_bkfst_sandwich",R.string.recipe_bkfst_sandwich);

        STRINGS = Collections.unmodifiableMap(bMap);
    }
    public static final String DATABASE_NAME = "caloriesDb";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "food_table";
    public static final String FOOD_NAME = "food";
    public static final String CALORIES = "cal";
    public static final String DATE_ADDED = "date";
    public static final String KEY_ID = "_id";

}
