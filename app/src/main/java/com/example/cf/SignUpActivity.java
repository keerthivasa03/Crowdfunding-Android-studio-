package com.example.cf;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cf.R;
import com.example.cf.loginpage;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        mAuth = FirebaseAuth.getInstance();

        EditText emailEditText = findViewById(R.id.emailEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        EditText conformpasswordEditText = findViewById(R.id.conform_password_input);
        Button signupButton = findViewById(R.id.signupButton);

        signupButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String conformpassword = conformpasswordEditText.getText().toString();

            if (!email.isEmpty() && !password.isEmpty() && !conformpassword.isEmpty() && password.equals(conformpassword)) {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignUpActivity.this, "Sign up successful.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUpActivity.this, loginpage.class));
                                finish();
                            } else {
                                Toast.makeText(SignUpActivity.this, "Sign up failed.", Toast.LENGTH_SHORT).show();
                            }
                        });
            } else {
                Toast.makeText(SignUpActivity.this, "Please fill all fields.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

