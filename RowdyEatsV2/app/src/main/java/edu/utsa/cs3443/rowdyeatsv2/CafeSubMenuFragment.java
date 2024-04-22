package edu.utsa.cs3443.rowdyeatsv2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;

import edu.utsa.cs3443.rowdyeatsv2.Model.FoodRecord;
import edu.utsa.cs3443.rowdyeatsv2.Model.Restaurant;
import edu.utsa.cs3443.rowdyeatsv2.adapters.CafeRecyclerViewAdapter;
import edu.utsa.cs3443.rowdyeatsv2.Model.FoodPreset;

public class CafeSubMenuFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cafe_menu_secondary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CafeSubMenuFragmentArgs args = CafeSubMenuFragmentArgs.fromBundle(getArguments());

        Restaurant parentModel = args.getModel();

        // sets toolbar text
        Toolbar subMenuToolbar = view.findViewById(R.id.toolbar);
        subMenuToolbar.setTitle(parentModel.getTitle());

        subMenuToolbar.setNavigationOnClickListener(v -> {
            //What to do on back clicked
            Navigation.findNavController(view).navigate(R.id.action_cafeSubMenuFragment_to_cafeTopMenuFragment);
        });

        RecyclerView recyclerView = view.findViewById(R.id.cafeSubRV);

        // created new array list..
        ArrayList<FoodPreset> recyclerDataArrayList = FoodPreset.getModels(view.getContext().getAssets(),parentModel);

        // added data from arraylist to adapter class.
        CafeRecyclerViewAdapter adapter = new CafeRecyclerViewAdapter(recyclerDataArrayList, view.getContext());

        // nutrition facts
        View bottomSheet = view.findViewById(R.id.bottom_sheet_nutrition);
        BottomSheetBehavior sheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheet.findViewById(R.id.btn_close_sheet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        MainActivity activity = (MainActivity)getActivity(); // TODO : bad

        // onclick
        adapter.setOnClickListener(new CafeRecyclerViewAdapter.OnClickListener() {
            @Override
            public void onAddToLog(int position, FoodPreset model) {
                activity.showTrackerDialog(new TrackerFragment.RefreshListener() {
                    @Override
                    public void onRefresh() {
                        // do nothing
                    }
                },new FoodRecord(model.getFoodName(),model.getCalories()));
            }

            @Override
            public void onOpenNutritionFacts(int position, FoodPreset model) {
                TextView bottomSheetTitle = bottomSheet.findViewById(R.id.food_name_head);
                bottomSheetTitle.setText(model.getFoodName());
                TextView bottomSheetBody = bottomSheet.findViewById(R.id.nutrition_body);
                bottomSheetBody.setText(model.getNutritionText());
                sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }

        });

        // setting grid layout manager to implement grid view.
        // in this method '2' represents number of columns to be displayed in grid view.
        Context rvContext = recyclerView.getContext();
        LinearLayoutManager layoutManager = new LinearLayoutManager(rvContext,LinearLayoutManager.VERTICAL,false);
        recyclerView.addItemDecoration(new DividerItemDecoration(rvContext,
                layoutManager.getOrientation()));

        // at last set adapter to recycler view.
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
