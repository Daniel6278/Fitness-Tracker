package edu.utsa.cs3443.rowdyeatsv2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import edu.utsa.cs3443.rowdyeatsv2.adapters.CustomDataAdapter;
import edu.utsa.cs3443.rowdyeatsv2.Model.FoodRecord;

public class TrackerFragment extends Fragment {

    private ListView listView;
    private CustomDataAdapter foodAdapter;
    private ArrayList<FoodRecord> foodArrayList;
    private RefreshListener refreshListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        foodArrayList = new ArrayList<FoodRecord>();
        foodAdapter = new CustomDataAdapter(getActivity(), R.layout.list_row_recorded_food, foodArrayList);
        foodAdapter.setNotifyOnChange(true); // changes to the ArrayList refresh the adapter
        refreshListener = new RefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        };
        return inflater.inflate(R.layout.fragment_tracker, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = view.findViewById(R.id.listView);
        listView.setAdapter(foodAdapter);

        FloatingActionButton fab = view.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).showTrackerDialog(refreshListener);
            }
        });

        refreshData();
    }

    public interface RefreshListener {
        public void onRefresh();
    }

    private void refreshData() {
        foodArrayList = ((MainActivity)getActivity()).getDba().getAllFood();
        foodAdapter.clear();
        foodAdapter.addAll(foodArrayList);
        //foodAdapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FoodRecord f = foodArrayList.get(position);
                if (id == R.id.btn_delete) {
                    // deletes a logged meal
                    ((MainActivity)getActivity()).deleteFoodRecord(f);
                    refreshData();
                }
                else if (id == R.id.btn_edit) {
                    // beams a logged meal’s model to the dialog for user edit
                    ((MainActivity)getActivity()).showTrackerDialog(refreshListener,f);
                }
            }
        });
    }
}
