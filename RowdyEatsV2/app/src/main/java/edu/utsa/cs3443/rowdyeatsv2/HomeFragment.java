package edu.utsa.cs3443.rowdyeatsv2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.io.*;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    public HomeFragment(){
        // require a empty public constructor
    }

    private boolean isMenu1Visible = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Button btnMenu1 = getView().findViewById(R.id.btnMenu1);
        final TextView txtDropdown1 = getView().findViewById(R.id.txtDropdown1);

        btnMenu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toggle visibility of the dropdown text
                isMenu1Visible = !isMenu1Visible;
                txtDropdown1.setVisibility(isMenu1Visible ? View.VISIBLE : View.GONE);
            }
        });

        // Repeat for other buttons and text views
    }
}