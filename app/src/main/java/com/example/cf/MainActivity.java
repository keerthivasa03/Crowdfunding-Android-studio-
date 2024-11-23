package com.example.cf;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set the listener for BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.navigation_home) {
                    // Home icon clicked
                    Toast.makeText(MainActivity.this, "Home Selected", Toast.LENGTH_SHORT).show();
                    return true;
                }
                 else if (itemId == R.id.navigation_donate) {
                    // Donate icon clicked
                    Toast.makeText(MainActivity.this, "Donate Selected", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, FundraiserDetailsActivity.class);
                    startActivity(intent); // Ensure this line is included to open the new activity
                    return true;
                }

                return false;
            }
        });
    }
}
