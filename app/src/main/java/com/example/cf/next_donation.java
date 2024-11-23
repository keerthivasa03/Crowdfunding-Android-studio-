package com.example.cf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class next_donation extends AppCompatActivity {

    ImageView right,left;
    Button pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_next_donation);

        right=findViewById(R.id.next_button);
        left=findViewById(R.id.previous_button);
        pay=findViewById(R.id.donate_button);

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(next_donation.this, next_donation.class);
                startActivity(intent);
                finish();
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(next_donation.this, FundraiserDetailsActivity.class);
                startActivity(intent);
                finish();
            }
        });
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(next_donation.this, payment.class);
                startActivity(intent);
                finish();
            }
        });
    }
}