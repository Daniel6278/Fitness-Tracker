package edu.utsa.cs3443.rowdyeatsv2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
    private boolean isTextChangeHandled = true;
    private String nameTextBoxContents = "";

    private void writeNameToPrefs(SharedPreferences sharedPref, String name) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.preferences_name_key), name);
        editor.apply();

        Toast.makeText(getContext(), "Name Changed!", Toast.LENGTH_LONG).show();
    }

    private boolean[] areMenusVisible = {false,false,false,false};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.println(Log.INFO, "HomeFragment", "onCreateView");

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
                nameTextBoxContents = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(nameTextBoxContents)) {
                    isTextChangeHandled = false;
                }
                nameTextBoxContents = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isTextChangeHandled) return; // ignore if text box’s contents stayed the same
                if (!nameBox.hasFocus()) return; // ignore events not caused by user’s typing

                Log.println(Log.INFO, "TextWatcher", "name text box contents edited");

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
                        writeNameToPrefs(sharedPref, s.toString());
                    }
                }, NAME_SAVE_DELAY_MS);

                isTextChangeHandled = true;
            }
        });

        final int[] btnsIds = {
                R.id.btnMenu1,
                R.id.btnMenu2,
                R.id.btnMenu3,
                R.id.btnMenu4,
        };
        final int[] dropdownsIds = {
                R.id.txtDropdown1,
                R.id.txtDropdown2,
                R.id.txtDropdown3,
                R.id.txtDropdown4,
        };

        for (int i = 0; i < btnsIds.length; ++i) {

            //Drop down buttons on HomeScreen
            final Button btnMenu = view.findViewById(btnsIds[i]);
            final TextView txtDropdown = view.findViewById(dropdownsIds[i]);

            final int finalI = i;
            btnMenu.setOnClickListener(new View.OnClickListener() {
                final int btnIndex = finalI;
                @Override
                public void onClick(View _view) {
                    // Toggle visibility of the dropdown text
                    areMenusVisible[btnIndex] = !areMenusVisible[btnIndex];
                    txtDropdown.setVisibility(areMenusVisible[btnIndex] ? View.VISIBLE : View.GONE);
                }
            });
        }
    }
}