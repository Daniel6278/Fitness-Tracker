package edu.utsa.cs3443.rowdyeatsv2.data;

import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CSVReader {

    private static final String DELIMIT_REGEX = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)"; // https://stackoverflow.com/a/15739087
    public static ArrayList<String[]> readStrings(AssetManager assetManager,String path) {
        InputStreamReader is;
        try {
            is = new InputStreamReader(assetManager.open(path));
        } catch (IOException e) {
            Log.e("CSVReader","open CSV file failed");
            return null;
        }

        BufferedReader reader = new BufferedReader(is);

        ArrayList<String[]> parsed = new ArrayList<>();

        try {
            reader.readLine(); // 1st line is columns’ headings
            String line;
            while ((line = reader.readLine()) != null) {
                parsed.add(line.split(DELIMIT_REGEX));
            }
        }
        catch (IOException e) {
            Log.e("CSVReader","read CSV file failed");
            return null;
        }
        return parsed;
    }
}
