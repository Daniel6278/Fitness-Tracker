package edu.utsa.cs3443.rowdyeatsv2.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;
import edu.utsa.cs3443.rowdyeatsv2.R;

import java.util.Calendar;

public class HomeFragmentModel {

    private Context context;

    public HomeFragmentModel(Context context) {
        this.context = context;
    }

    public void writeNameToPrefs(String nameKey, String name) {
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preferences_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(nameKey, name);
        editor.apply();

        Toast.makeText(context, "Name Changed!", Toast.LENGTH_LONG).show();
    }

    public String getUserName(String nameKey, String defaultName) {
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preferences_file_key), Context.MODE_PRIVATE);
        return sharedPref.getString(nameKey, defaultName);
    }

    public String getGreeting() {
        Calendar calendar = Calendar.getInstance();
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        return "Good " + (currentHour >= 3 && currentHour < 12 ? "Morning" : (currentHour < 17 ? "Afternoon" : "Evening")) + ",";
    }
}
