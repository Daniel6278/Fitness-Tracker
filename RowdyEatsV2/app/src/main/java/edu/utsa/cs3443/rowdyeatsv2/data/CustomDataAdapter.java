package edu.utsa.cs3443.rowdyeatsv2.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import edu.utsa.cs3443.rowdyeatsv2.model.Food;
import edu.utsa.cs3443.rowdyeatsv2.R;

import java.util.ArrayList;

public class CustomDataAdapter extends ArrayAdapter<Food> {
    private int layoutResource;
    private Activity activity;
    private ArrayList<Food> foodArrayList;

    public CustomDataAdapter(@NonNull Activity act, int resource, ArrayList<Food> data) {
        super(act, resource, data);
        this.layoutResource = resource;
        this.activity = act;
        this.foodArrayList = data;
    }

    @Override
    public int getCount() {
        return foodArrayList.size();
    }

    @Nullable
    @Override
    public Food getItem(int position) {
        return foodArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(activity);
            row = inflater.inflate(layoutResource, parent, false);
            holder = new ViewHolder();
            holder.foodName = row.findViewById(R.id.name);
            holder.foodCalories = row.findViewById(R.id.calories);
            holder.foodDate = row.findViewById(R.id.dateText);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        Food food = getItem(position);
        if (food != null) {
            holder.foodName.setText(food.getFoodName());
            holder.foodCalories.setText(String.format("%d cal", food.getCalories()));
            holder.foodDate.setText(food.getRecordDate());
        }

        return row;
    }

    public class ViewHolder {
        TextView foodName, foodCalories, foodDate;
    }
}
