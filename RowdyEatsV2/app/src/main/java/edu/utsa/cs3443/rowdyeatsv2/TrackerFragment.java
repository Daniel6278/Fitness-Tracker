package edu.utsa.cs3443.rowdyeatsv2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import edu.utsa.cs3443.rowdyeatsv2.adapters.CustomDataAdapter;
import edu.utsa.cs3443.rowdyeatsv2.data.DatabaseHandler;
import edu.utsa.cs3443.rowdyeatsv2.model.FoodRecord;

public class TrackerFragment extends Fragment {

    private ListView listView;
    private CustomDataAdapter foodAdapter;
    private ArrayList<FoodRecord> foodArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tracker, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.listView);

        FloatingActionButton fab = view.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).showTrackerDialog(new RefreshListener() {
                    @Override
                    public void onRefresh() {
                        refreshData();
                    }
                });
            }
        });

        refreshData();
    }

    public interface RefreshListener {
        public void onRefresh();
    }

    private void refreshData() {
        foodArrayList = ((MainActivity)getActivity()).getDba().getAllFood();
        foodAdapter = new CustomDataAdapter(getActivity(), R.layout.list_row_recorded_food, foodArrayList);
        listView.setAdapter(foodAdapter);
    }
}
