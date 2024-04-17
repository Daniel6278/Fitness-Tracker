package edu.utsa.cs3443.rowdyeatsv2.Model;

import java.util.ArrayList;

import edu.utsa.cs3443.rowdyeatsv2.R;

public class Restaurant {

    public static ArrayList<Restaurant> getModels() {
        ArrayList<Restaurant> list = new ArrayList<>();
        // add data to array list
        // TODO : make this stuff not hardcoded
        list.add(new Restaurant("Chick-fil-A", R.drawable.chickfilalogo));
        list.add(new Restaurant("Subway",R.drawable.subwaylogo));
        list.add(new Restaurant("SushiC",R.drawable.sushiclogo));
        list.add(new Restaurant("Einstein Bros. Bagels",R.drawable.restaurant_logo_einsteinbrosbagels));
        list.add(new Restaurant("Dragon Bowl Z",R.drawable.restaurant_logo_dragonbowlz));
        return list;
    }
    private String title;
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

    public Restaurant(String title, int imgId) {
        this.title = title;
        this.imgId = imgId;
    }
}
