package com.example.cf;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginpage extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private TextView signupTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        mAuth = FirebaseAuth.getInstance();
        initializeViews();
        loginButton.setOnClickListener(v -> loginUser());

        signupTextView.setOnClickListener(v -> navigateToSignUp());
    }

    private void initializeViews() {
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        signupTextView = findViewById(R.id.signupTextView);
    }

    private void loginUser() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please fill all fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        loginButton.setEnabled(false);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    loginButton.setEnabled(true);
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        navigateToMain();
                    } else {
                        Toast.makeText(loginpage.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void navigateToMain() {
        Intent intent = new Intent(loginpage.this, choosepath.class);
        startActivity(intent);
        finish();
    }

    private void navigateToSignUp() {
        Intent intent = new Intent(loginpage.this, SignUpActivity.class);
        startActivity(intent);
    }
}
