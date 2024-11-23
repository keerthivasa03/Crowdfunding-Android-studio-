package com.example.cf;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AmountDetailsFragment extends Fragment {
    private EditText etAmount;
    private RadioGroup currencyGroup;
    private Spinner spinnerCategory;
    private Button btnNext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_amount_details, container, false);

        etAmount = view.findViewById(R.id.etAmount);
        currencyGroup = view.findViewById(R.id.currencyGroup);
        spinnerCategory = view.findViewById(R.id.spinnerCategory);
        btnNext = view.findViewById(R.id.btnNext);

        // Get PersonalDetails from the previous fragment
        PersonalDetails personalDetails;
        if (getArguments() != null) {
            personalDetails = (PersonalDetails) getArguments().getSerializable("personalDetails");
        } else {
            personalDetails = null;
        }

        btnNext.setOnClickListener(v -> {
            if (currencyGroup.getCheckedRadioButtonId() == -1) {
                Toast.makeText(getActivity(), "Please select a currency", Toast.LENGTH_SHORT).show();
                return;
            }

            String currency = ((RadioButton) view.findViewById(currencyGroup.getCheckedRadioButtonId())).getText().toString();
            String category = spinnerCategory.getSelectedItem().toString();
            String amount = etAmount.getText().toString();

            AmountDetails amountDetails = new AmountDetails(amount, currency, category);

            // Retrieve PersonalDetails from arguments
            PersonalDetails PersonalDetails = (PersonalDetails) getArguments().getSerializable("personalDetails");

            // Pass data to the next fragment
            MedicalDetailsFragment medicalDetailsFragment = new MedicalDetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("personalDetails", PersonalDetails);
            bundle.putSerializable("amountDetails", amountDetails);
            medicalDetailsFragment.setArguments(bundle);

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, medicalDetailsFragment)
                    .addToBackStack(null)
                    .commit();
        });


        return view;
    }
}
