package com.example.cf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class payment extends AppCompatActivity {

    ImageView back_arrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment);
        back_arrow=findViewById(R.id.back_arrow);

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(payment.this, FundraiserDetailsActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}