package edu.utsa.cs3443.rowdyeatsv2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.fragment.app.Fragment;

import edu.utsa.cs3443.rowdyeatsv2.data.DatabaseHandler;
import edu.utsa.cs3443.rowdyeatsv2.data.CustomDataAdapter;
import edu.utsa.cs3443.rowdyeatsv2.model.Food;

import java.util.ArrayList;

public class CafeFragment extends Fragment {
    private ListView listView;
    private CustomDataAdapter foodAdapter;
    private ArrayList<Food> foodArrayList;
    private DatabaseHandler dba;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cafe, container, false);
        listView = view.findViewById(R.id.listViewCafe);
        dba = new DatabaseHandler(getActivity());
        refreshData();
        return view;
    }

    private void refreshData() {
        foodArrayList = dba.getAllFood();
        foodAdapter = new CustomDataAdapter(getActivity(), R.layout.list_row, foodArrayList);
        listView.setAdapter(foodAdapter);
    }
}