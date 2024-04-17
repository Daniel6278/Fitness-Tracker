package edu.utsa.cs3443.rowdyeatsv2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.utsa.cs3443.rowdyeatsv2.adapters.CafeRecyclerViewAdapter;
import edu.utsa.cs3443.rowdyeatsv2.model.FoodPreset;

public class CafeFragment2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cafe_2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.cafeRV);

        // created new array list..
        ArrayList<FoodPreset> recyclerDataArrayList = FoodPreset.getModels();

        // added data from arraylist to adapter class.
        CafeRecyclerViewAdapter adapter = new CafeRecyclerViewAdapter(recyclerDataArrayList, view.getContext());

        // onclick
        adapter.setOnClickListener(new CafeRecyclerViewAdapter.OnClickListener() {
            @Override
            public void onClick(int position, FoodPreset model) {


            }
        });

        // setting grid layout manager to implement grid view.
        // in this method '2' represents number of columns to be displayed in grid view.
        Context rvContext = recyclerView.getContext();
        LinearLayoutManager layoutManager = new LinearLayoutManager(rvContext);
        recyclerView.addItemDecoration(new DividerItemDecoration(rvContext,
                layoutManager.getOrientation()));

        // at last set adapter to recycler view.
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
