package edu.utsa.cs3443.rowdyeatsv2.Model;

import android.content.res.AssetManager;

import java.io.Serializable;
import java.util.ArrayList;

import edu.utsa.cs3443.rowdyeatsv2.R;
import edu.utsa.cs3443.rowdyeatsv2.data.CSVReader;
import edu.utsa.cs3443.rowdyeatsv2.data.Constants;

public class Restaurant implements Serializable {

    private static final String DATA_FILE_PATH = "csv/Restaurant/food_categories.csv";

    public static ArrayList<Restaurant> getModels(AssetManager assets) {
        ArrayList<Restaurant> list = new ArrayList<>();
        /*
        // add data to array list
        // TODO : make this stuff not hardcoded
        list.add(new Restaurant("Chick-fil-A", R.drawable.chickfilalogo));
        list.add(new Restaurant("Subway",R.drawable.subwaylogo));
        list.add(new Restaurant("SushiC",R.drawable.sushiclogo));
        list.add(new Restaurant("Einstein Bros. Bagels",R.drawable.restaurant_logo_einsteinbrosbagels));
        list.add(new Restaurant("Dragon Bowl Z",R.drawable.restaurant_logo_dragonbowlz));
        */
        ArrayList<String[]> csvRawData = CSVReader.readStrings(assets,DATA_FILE_PATH);
        for (String[] strings : csvRawData) {
            if (strings.length < 3) {
                // when no image provided in CSV
                list.add(new Restaurant(strings[0],R.drawable.app_logo_transparent,strings[1]));
                continue;
            }
            // when image is provided in CSV
            list.add(new Restaurant(strings[0],Constants.DRAWABLES.get(strings[2]),strings[1]));
        }
        return list;
    }
    private String title, foodsFileName;
    private int imgId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public Restaurant(String title, int imgId, String foodsFileName) {
        this.title = title;
        this.imgId = imgId;
        this.foodsFileName = foodsFileName;
    }

    public String getFoodsFileName() {
        return foodsFileName;
    }
}
