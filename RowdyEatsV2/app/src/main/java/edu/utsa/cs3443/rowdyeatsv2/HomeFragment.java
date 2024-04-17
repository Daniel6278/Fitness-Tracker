package edu.utsa.cs3443.rowdyeatsv2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

public class HomeFragment extends Fragment {

    public HomeFragment(){
        // require a empty public constructor
    }

    private long lastTypedTime = 0;

    private void writeNameToPrefs(String name) {
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.preferences_name_key), name);
        editor.apply();

        Toast.makeText(getContext(), "Name Changed!", Toast.LENGTH_LONG).show();
    }

    private boolean isMenu1Visible = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // greets user with "Good (Morning, Afternoon, Evening)" depending on time of day
        Calendar calendar = Calendar.getInstance();
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        TextView greeting = view.findViewById(R.id.userGreeting);
        greeting.setText("Good " + (currentHour >= 3 && currentHour < 12 ? "Morning" : (currentHour < 17 ? "Afternoon" : "Evening")) + ",");

        // loads user’s name
        SharedPreferences sharedPref = getContext().getSharedPreferences(getString(R.string.preferences_file_key), Context.MODE_PRIVATE);
        String userName = sharedPref.getString(getString(R.string.preferences_name_key), "");
        EditText nameBox = view.findViewById(R.id.editTextName);
        nameBox.setText(userName);

        // listens for changed to user’s name
        nameBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                final int NAME_SAVE_DELAY_MS = 500; // don’t write to the preferences file unless the text box was unchanged for this number of milliseconds

                final long epoch = System.currentTimeMillis();
                lastTypedTime = epoch;

                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (lastTypedTime != epoch) {
                            return;
                        }
                        writeNameToPrefs(s.toString());
                    }
                }, NAME_SAVE_DELAY_MS);
            }
        });


        //Drop down buttons on HomeScreen
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

        Button btnMenu2 = view.findViewById(R.id.btnMenu2);
        final TextView txtDropdown2 = view.findViewById(R.id.txtDropdown2);
        btnMenu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                // Toggle visibility of the dropdown text
                isMenu1Visible = !isMenu1Visible;
                txtDropdown2.setVisibility(isMenu1Visible ? View.VISIBLE : View.GONE);
            }
        });

        Button btnMenu3 = view.findViewById(R.id.btnMenu3);
        final TextView txtDropdown3 = view.findViewById(R.id.txtDropdown3);
        btnMenu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                // Toggle visibility of the dropdown text
                isMenu1Visible = !isMenu1Visible;
                txtDropdown3.setVisibility(isMenu1Visible ? View.VISIBLE : View.GONE);
            }
        });

        Button btnMenu4 = view.findViewById(R.id.btnMenu4);
        final TextView txtDropdown4 = view.findViewById(R.id.txtDropdown4);
        btnMenu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                // Toggle visibility of the dropdown text
                isMenu1Visible = !isMenu1Visible;
                txtDropdown4.setVisibility(isMenu1Visible ? View.VISIBLE : View.GONE);
            }
        });
    }
}