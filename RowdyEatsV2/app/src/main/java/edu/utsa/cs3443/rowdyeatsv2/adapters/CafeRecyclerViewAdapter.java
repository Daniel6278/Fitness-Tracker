package edu.utsa.cs3443.rowdyeatsv2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.utsa.cs3443.rowdyeatsv2.R;
import edu.utsa.cs3443.rowdyeatsv2.Model.FoodPreset;

public class CafeRecyclerViewAdapter extends RecyclerView.Adapter<CafeRecyclerViewAdapter.RecyclerViewHolder> {
    private OnClickListener onClickListener;
    private ArrayList<FoodPreset> cafeArrayList;
    private Context mcontext;

    public CafeRecyclerViewAdapter(ArrayList<FoodPreset> recyclerDataArrayList, Context mcontext) {
        this.cafeArrayList = recyclerDataArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_preset_food, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview and imageview.
        FoodPreset recyclerData = cafeArrayList.get(position);
        holder.tvName.setText(recyclerData.getFoodName());
        holder.tvCalories.setText(Integer.toString(recyclerData.getCalories()) + " cal");
        holder.iv.setImageResource(recyclerData.getImgId());

        holder.btnAddToLog.setOnClickListener(view -> {
            if (onClickListener != null) {
                onClickListener.onAddToLog(position, cafeArrayList.get(position));
            }
        });
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onAddToLog(int position, FoodPreset model);
        void onOpenNutritionFacts(int position, FoodPreset model);
    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return cafeArrayList.size();
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName, tvCalories;
        private ImageView iv;

        private Button btnAddToLog;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.food_preset_name);
            tvCalories = itemView.findViewById(R.id.food_preset_calories);
            iv = itemView.findViewById(R.id.food_preset_image);

            btnAddToLog = itemView.findViewById(R.id.btn_add_to_log);
        }
    }
}
