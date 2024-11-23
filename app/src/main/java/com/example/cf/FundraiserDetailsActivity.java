package com.example.cf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class FundraiserDetailsActivity extends AppCompatActivity {

    ImageView right,left,toolbar_icon,filter_icon;
    Button pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_donationpage);

        right=findViewById(R.id.next_button);
        left=findViewById(R.id.previous_button);
        pay=findViewById(R.id.donate_button);
        toolbar_icon=findViewById(R.id.toolbar_icon);
        filter_icon=findViewById(R.id.filter_icon);

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FundraiserDetailsActivity.this, next_donation.class);
                startActivity(intent);
                finish();
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FundraiserDetailsActivity.this, previous_donation.class);
                startActivity(intent);
                finish();
            }
        });
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FundraiserDetailsActivity.this, payment.class);
                startActivity(intent);
                finish();
            }
        });
        toolbar_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FundraiserDetailsActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });
        filter_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FundraiserDetailsActivity.this, FilterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}