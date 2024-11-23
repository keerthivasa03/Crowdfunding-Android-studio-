package com.example.cf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class FilterActivity extends AppCompatActivity {

    private RadioGroup emergencyTypeGroup;
    private Spinner stateSpinner, citySpinner;
    private Button applyFilterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        // Initialize views
        emergencyTypeGroup = findViewById(R.id.emergency_type_group);
        stateSpinner = findViewById(R.id.state_spinner);
        citySpinner = findViewById(R.id.city_spinner);
        applyFilterButton = findViewById(R.id.apply_filter_button);

        // Populate Spinners (example data)
        populateStateSpinner();
        populateCitySpinner();

        // Apply filter button listener
        applyFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        Intent intent = new Intent(FilterActivity.this, FundraiserDetailsActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }

    private void populateStateSpinner() {
        // Add states to the spinner
        String[] states = {"Select State", "State 1", "State 2", "State 3"};
        SpinnerAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, states);
        stateSpinner.setAdapter(adapter);
    }

    private void populateCitySpinner() {
        // Add cities to the spinner
        String[] cities = {"Select City", "City 1", "City 2", "City 3"};
        SpinnerAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cities);
        citySpinner.setAdapter(adapter);
    }

    private String getSelectedEmergencyType() {
        int selectedId = emergencyTypeGroup.getCheckedRadioButtonId();
        if (selectedId != -1) {
            RadioButton selectedButton = findViewById(selectedId);
            return selectedButton.getText().toString();
        }
        return "";
    }

        }