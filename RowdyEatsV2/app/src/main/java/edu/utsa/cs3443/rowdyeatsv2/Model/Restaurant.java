package edu.utsa.cs3443.rowdyeatsv2.model;

import java.util.ArrayList;

import edu.utsa.cs3443.rowdyeatsv2.R;

public class Restaurant {

    public static ArrayList<Restaurant> getModels() {
        ArrayList<Restaurant> list = new ArrayList<>();
        // add data to array list
        // TODO : make this stuff not hardcoded
        list.add(new Restaurant("Chick-Fil-A", R.drawable.chickfilalogo));
        list.add(new Restaurant("Subway",R.drawable.subwaylogo));
        list.add(new Restaurant("Sushi-C",R.drawable.sushiclogo));
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
