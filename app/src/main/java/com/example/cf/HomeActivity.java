package com.example.cf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private Button btnDonate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnDonate = findViewById(R.id.btnDonate);

        btnDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the donation page or fundraiser details activity
                navigateToDonationPage();
            }
        });
    }

    private void navigateToDonationPage() {
        // Assuming you have a DonateActivity or FundraiserListActivity
        Intent intent = new Intent(HomeActivity.this,FundraiserDetailsActivity.class);
        startActivity(intent);
    }
}
