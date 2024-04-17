package edu.utsa.cs3443.rowdyeatsv2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.utsa.cs3443.rowdyeatsv2.adapters.CafeRecyclerViewAdapter;
import edu.utsa.cs3443.rowdyeatsv2.model.FoodPreset;

public class CafeSubMenuFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cafe_menu_secondary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CafeSubMenuFragmentArgs args = CafeSubMenuFragmentArgs.fromBundle(getArguments());

        // sets toolbar text
        Toolbar subMenuToolbar = view.findViewById(R.id.toolbar);
        subMenuToolbar.setTitle(args.getHead());

        subMenuToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                Navigation.findNavController(view).navigate(R.id.action_cafeSubMenuFragment_to_cafeTopMenuFragment);
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.cafeSubRV);

        // created new array list..
        ArrayList<FoodPreset> recyclerDataArrayList = FoodPreset.getModels(args.getHead());

        // added data from arraylist to adapter class.
        CafeRecyclerViewAdapter adapter = new CafeRecyclerViewAdapter(recyclerDataArrayList, view.getContext());

        MainActivity activity = (MainActivity)getActivity(); // TODO : bad

        // onclick
        adapter.setOnClickListener(new CafeRecyclerViewAdapter.OnClickListener() {
            @Override
            public void onAddToLog(int position, FoodPreset model) {
                activity.showTrackerDialog();
            }

            @Override
            public void onOpenNutritionFacts(int position, FoodPreset model) {

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
