package com.example.cf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.Button;

public class main_fragment extends AppCompatActivity {

    Button btnFragment1, btnFragment2, btnFragment3,btnFragment4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment);

        btnFragment1 = findViewById(R.id.btnFragment1);
        btnFragment2 = findViewById(R.id.btnFragment2);
        btnFragment3 = findViewById(R.id.btnFragment3);
        btnFragment4=findViewById(R.id.btnFragment4);
        // Initially load the PersonalDetailsFragment
        loadFragment(new PersonalDetailsFragment());

        btnFragment1.setOnClickListener(v -> loadFragment(new PersonalDetailsFragment()));
        btnFragment2.setOnClickListener(v -> loadFragment(new AmountDetailsFragment()));
        btnFragment3.setOnClickListener(v -> loadFragment(new MedicalDetailsFragment()));
        btnFragment4.setOnClickListener(v -> loadFragment(new BankInfoFragment()));
    }

    private void loadFragment(Fragment fragment) {
        // Begin a new transaction
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace the current fragment with the new one
        transaction.replace(R.id.fragment_container, fragment);

        // Remove all fragments from the back stack, ensuring only the current fragment is visible
        transaction.setReorderingAllowed(true);
        transaction.commitAllowingStateLoss();
    }
}
