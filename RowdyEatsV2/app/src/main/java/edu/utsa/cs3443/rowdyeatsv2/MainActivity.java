package edu.utsa.cs3443.rowdyeatsv2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.io.*;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView
        .OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView
                = findViewById(R.id.bottomNavigationView);

        bottomNavigationView
                .setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);
    }
    CafeFragment cafeFragment = new CafeFragment();
    CalculatorFragment calculatorFragment = new CalculatorFragment();
    HomeFragment homeFragment = new HomeFragment();
    RecipesFragment recipesFragment = new RecipesFragment();
    TrackerFragment trackerFragment = new TrackerFragment();

    @Override
    public boolean
    onNavigationItemSelected(@NonNull MenuItem item)
    {

        switch (item.getItemId()) {
            case R.id.home:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, homeFragment)
                        .commit();
                return true;

            case R.id.tracker:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, trackerFragment)
                        .commit();
                return true;

            case R.id.cafe:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, cafeFragment)
                        .commit();
                return true;

            case R.id.recipes:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, recipesFragment)
                        .commit();
                return true;

            case R.id.calculator:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, calculatorFragment)
                        .commit();
                return true;
        }
        return false;
    }
}