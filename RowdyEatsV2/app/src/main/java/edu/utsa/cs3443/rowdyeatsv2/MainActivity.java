package edu.utsa.cs3443.rowdyeatsv2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private boolean isMenu1Visible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMenu1 = findViewById(R.id.btnMenu1);
        final TextView txtDropdown1 = findViewById(R.id.txtDropdown1);

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
