package edu.utsa.cs3443.rowdyeatsv2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.utsa.cs3443.rowdyeatsv2.R;
import edu.utsa.cs3443.rowdyeatsv2.model.Restaurant;

public class RestaurantsRecyclerViewAdapter extends RecyclerView.Adapter<RestaurantsRecyclerViewAdapter.RecyclerViewHolder> {
    private OnClickListener onClickListener;
    private ArrayList<Restaurant> recipesArrayList;
    private Context mcontext;

    public RestaurantsRecyclerViewAdapter(ArrayList<Restaurant> recyclerDataArrayList, Context mcontext) {
        this.recipesArrayList = recyclerDataArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_presets_category, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview and imageview.
        Restaurant recyclerData = recipesArrayList.get(position);
        holder.tvName.setText(recyclerData.getTitle());
        holder.iv.setImageResource(recyclerData.getImgId());

        holder.itemView.setOnClickListener(view -> {
            if (onClickListener != null) {
                onClickListener.onClick(position, recipesArrayList.get(position));
            }
        });
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int position, Restaurant model);
    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return recipesArrayList.size();
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private ImageView iv;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.category_name);
            iv = itemView.findViewById(R.id.category_image);
        }
    }
}
