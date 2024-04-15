package edu.utsa.cs3443.rowdyeatsv2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    public HomeFragment(){
        // require a empty public constructor
    }

    private boolean isMenu1Visible = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Button btnMenu1 = view.findViewById(R.id.btnMenu1);
        final TextView txtDropdown1 = view.findViewById(R.id.txtDropdown1);

        btnMenu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                // Toggle visibility of the dropdown text
                isMenu1Visible = !isMenu1Visible;
                txtDropdown1.setVisibility(isMenu1Visible ? View.VISIBLE : View.GONE);
            }
        });

        // Repeat for other buttons and text views
    }
}