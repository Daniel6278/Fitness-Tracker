package edu.utsa.cs3443.rowdyeatsv2.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import edu.utsa.cs3443.rowdyeatsv2.Model.FoodRecord;
import edu.utsa.cs3443.rowdyeatsv2.Model.Recipe;
import edu.utsa.cs3443.rowdyeatsv2.R;

import java.util.ArrayList;

public class CustomDataAdapter extends ArrayAdapter<FoodRecord> {
    private int layoutResource;
    private Activity activity;
    private ArrayList<FoodRecord> foodArrayList;
    private OnClickListener onEditClickListener, onDeleteClickListener;

    public CustomDataAdapter(@NonNull Activity act, int resource, ArrayList<FoodRecord> data) {
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
    public FoodRecord getItem(int position) {
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

            row.findViewById(R.id.btn_delete).setOnClickListener(v -> {
                if (onDeleteClickListener != null) {
                    onDeleteClickListener.onClick(position,getItem(position));
                    // do not use the food variable here. must call getItem to get the latest model object after edit
                }
            });
            row.findViewById(R.id.btn_edit).setOnClickListener(v -> {
                if (onEditClickListener != null) {
                    onEditClickListener.onClick(position,getItem(position));
                    // do not use the food variable here. must call getItem to get the latest model object after edit
                }
            });

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        @NotNull FoodRecord food = getItem(position);
        holder.foodName.setText(food.getFoodName());
        holder.foodCalories.setText(String.format("%d cal", food.getCalories()));
        holder.foodDate.setText(food.getRecordDateStr());

        return row;
    }

    public interface OnClickListener {
        void onClick(int position, FoodRecord model);
    }

    public void setOnDeleteClickListener(OnClickListener onClickListener) {
        this.onDeleteClickListener = onClickListener;
    }
    public void setOnEditClickListener(OnClickListener onClickListener) {
        this.onEditClickListener = onClickListener;
    }

    public class ViewHolder {
        TextView foodName, foodCalories, foodDate;
    }
}
