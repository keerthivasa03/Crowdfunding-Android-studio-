package com.example.cf;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PersonalDetailsFragment extends Fragment {

    private EditText etName, etEmail, etPhone, etAddress, etState, etPincode;
    private Button btnNext;
    private DatabaseReference databaseReference;

    public PersonalDetailsFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal__details, container, false);

        etName = view.findViewById(R.id.etName);
        etEmail = view.findViewById(R.id.etEmail);
        etPhone = view.findViewById(R.id.etPhone);
        etAddress = view.findViewById(R.id.etAddress);
        etState = view.findViewById(R.id.autoCompleteState);
        etPincode = view.findViewById(R.id.etPincode);
        btnNext = view.findViewById(R.id.btnNext);

        btnNext.setOnClickListener(v -> {
            PersonalDetails personalDetails = new PersonalDetails(
                    etName.getText().toString(),
                    etEmail.getText().toString(),
                    etPhone.getText().toString(),
                    etAddress.getText().toString(),
                    etState.getText().toString(),
                    etPincode.getText().toString()
            );

            // Pass personal details to the next fragment
            AmountDetailsFragment amountDetailsFragment = new AmountDetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("personalDetails", personalDetails);
            amountDetailsFragment.setArguments(bundle);

            // Switch to AmountDetailsFragment
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, amountDetailsFragment)
                    .addToBackStack(null)
                    .commit();
        });


        return view;
    }
}

