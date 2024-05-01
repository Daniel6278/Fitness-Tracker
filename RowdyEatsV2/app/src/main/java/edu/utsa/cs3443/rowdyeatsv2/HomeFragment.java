package edu.utsa.cs3443.rowdyeatsv2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;
import edu.utsa.cs3443.rowdyeatsv2.Model.HomeFragmentModel;

public class HomeFragment extends Fragment {

    private HomeFragmentModel model;
    private long lastTypedTime = 0;
    private boolean isTextChangeHandled = true;
    private String nameTextBoxContents = "";
    private Integer selectedMenuIndex = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        model = new HomeFragmentModel(getContext());
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView greeting = view.findViewById(R.id.userGreeting);
        greeting.setText(model.getGreeting());

        EditText nameBox = view.findViewById(R.id.editTextName);
        nameBox.setText(model.getUserName(getString(R.string.preferences_name_key), ""));

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
                handleNameChange(s, nameBox);
            }
        });

        setupMenuButtons(view);
    }

    private void handleNameChange(Editable s, EditText nameBox) {
        if (isTextChangeHandled || !nameBox.hasFocus()) return;

        final int NAME_SAVE_DELAY_MS = 500;
        final long epoch = System.currentTimeMillis();
        lastTypedTime = epoch;

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (lastTypedTime == epoch) {
                model.writeNameToPrefs(getString(R.string.preferences_name_key), s.toString());
            }
        }, NAME_SAVE_DELAY_MS);

        isTextChangeHandled = true;
    }

    private void setupMenuButtons(View view) {
        final int[] btnsIds = {
                R.id.btnMenu1, R.id.btnMenu2, R.id.btnMenu3, R.id.btnMenu4,
        };

        final int[] descriptionsStringsIds = {
                R.string.mainPageDescription_FoodLog,
                R.string.mainPageDescription_UTSADiningOptions,
                R.string.mainPageDescription_MealSuggestions,
                R.string.mainPageDescription_CalorieCalculator,
        };

        for (int i = 0; i < btnsIds.length; ++i) {
            Button btnMenu = view.findViewById(btnsIds[i]);
            MaterialCardView tooltipBox = view.findViewById(R.id.tooltip);
            TextView tooltipText = view.findViewById(R.id.tooltipTxt);

            final int btnIndex = i;  // Effectively final variable for lambda expression
            btnMenu.setOnClickListener(v -> {
                // Toggle visibility of the description text
                selectedMenuIndex = (selectedMenuIndex != null && selectedMenuIndex.equals(btnIndex)) ? null : btnIndex;
                tooltipText.setText(descriptionsStringsIds[btnIndex]);
                tooltipBox.setVisibility(selectedMenuIndex == null ? View.GONE : View.VISIBLE);
            });
        }
    }
}
