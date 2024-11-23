package com.example.cf;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import java.util.Collections;

public class BankInfoFragment extends Fragment {

    private EditText fullNameEditText, bankNameEditText, accountNumberEditText, ifscCodeEditText, contactNumberEditText;
    private Button submitButton;

    private MongoCollection<Document> bankInfoCollection;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the fragment's layout
        View rootView = inflater.inflate(R.layout.fragment_bank__details, container, false);

        // Initialize Views
        fullNameEditText = rootView.findViewById(R.id.full_name);
        bankNameEditText = rootView.findViewById(R.id.bank_name);
        accountNumberEditText = rootView.findViewById(R.id.account_number);
        ifscCodeEditText = rootView.findViewById(R.id.ifsc_code);
        contactNumberEditText = rootView.findViewById(R.id.contact_number);
        submitButton = rootView.findViewById(R.id.submit_button);

        // Initialize MongoDB
        initializeMongoDB();

        // Set Submit Button Click Listener
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitBankInfo();
                Intent intent = new Intent(requireContext(), HomeActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    private void initializeMongoDB() {
        try {
            // MongoDB connection settings
            String mongoURI = "10.0.2.2:27017"; // Replace with your IP if testing on a real device
            MongoClient mongoClient = MongoClients.create(MongoClientSettings.builder()
                    .applyToClusterSettings(builder -> builder.hosts(Collections.singletonList(new ServerAddress(mongoURI))))
                    .build());

            MongoDatabase mongoDatabase = mongoClient.getDatabase("CF"); // Database name
            bankInfoCollection = mongoDatabase.getCollection("bankdetails"); // Collection name

            Toast.makeText(getContext(), "Connected to MongoDB", Toast.LENGTH_SHORT).show();
        } catch (MongoException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "MongoDB connection failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void submitBankInfo() {
        // Get Values
        String fullName = fullNameEditText.getText().toString();
        String bankName = bankNameEditText.getText().toString();
        String accountNumber = accountNumberEditText.getText().toString();
        String ifscCode = ifscCodeEditText.getText().toString();
        String contactNumber = contactNumberEditText.getText().toString();

        // Validate Inputs
        if (TextUtils.isEmpty(fullName) || TextUtils.isEmpty(bankName) ||
                TextUtils.isEmpty(accountNumber) || TextUtils.isEmpty(ifscCode) ||
                TextUtils.isEmpty(contactNumber)) {
            Toast.makeText(getContext(), "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a new Document
        Document bankInfoDoc = new Document("fullName", fullName)
                .append("bankName", bankName)
                .append("accountNumber", accountNumber)
                .append("ifscCode", ifscCode)
                .append("contactNumber", contactNumber);

        // Insert into MongoDB using AsyncTask
        new InsertBankInfoTask().execute(bankInfoDoc);
    }

    private class InsertBankInfoTask extends AsyncTask<Document, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Document... documents) {
            try {
                // Insert the document into MongoDB
                bankInfoCollection.insertOne(documents[0]);
                return true;
            } catch (MongoException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                Toast.makeText(getContext(), "Bank information saved", Toast.LENGTH_SHORT).show();
                clearInputFields();
            } else {
                Toast.makeText(getContext(), "Failed to save bank information", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void clearInputFields() {
        fullNameEditText.setText("");
        bankNameEditText.setText("");
        accountNumberEditText.setText("");
        ifscCodeEditText.setText("");
        contactNumberEditText.setText("");
    }
}
