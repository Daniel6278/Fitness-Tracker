package edu.utsa.cs3443.rowdyeatsv2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import edu.utsa.cs3443.rowdyeatsv2.Model.FoodRecord;
import edu.utsa.cs3443.rowdyeatsv2.adapters.CustomDataAdapter;

public class TrackerFragment extends Fragment {

    private ListView listView;
    private CustomDataAdapter foodAdapter;
    private ArrayList<FoodRecord> foodArrayList;
    private RefreshListener refreshListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        foodArrayList = new ArrayList<>();
        foodAdapter = new CustomDataAdapter(getActivity(), R.layout.list_row_recorded_food, foodArrayList);
        foodAdapter.setNotifyOnChange(true); // changes to foodAdapter’s data refresh the UI
        refreshListener = this::refreshData;
        return inflater.inflate(R.layout.fragment_tracker, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = view.findViewById(R.id.listView);
        listView.setAdapter(foodAdapter);

        FloatingActionButton fab = view.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(v -> ((MainActivity)getActivity()).showTrackerDialog(refreshListener));

        refreshData();
    }

    public interface RefreshListener {
        void onRefresh();
    }

    private void refreshData() {
        foodArrayList = ((MainActivity)getActivity()).getDba().getAllFood();
        foodAdapter.clear();
        foodAdapter.addAll(foodArrayList);
        //foodAdapter.notifyDataSetChanged();

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Log.d("refreshData","item click listener");
            FoodRecord f = foodArrayList.get(position);
            switch (id) {
                case R.id.btn_delete:
                    // deletes a logged meal
                    ((MainActivity)getActivity()).deleteFoodRecord(f);
                    refreshData();
                    break;
                case R.id.btn_edit:
                    // beams a logged meal’s model to the dialog for user edit
                    ((MainActivity)getActivity()).showTrackerDialog(refreshListener,f);
                    break;
            }
        });
    }
}
