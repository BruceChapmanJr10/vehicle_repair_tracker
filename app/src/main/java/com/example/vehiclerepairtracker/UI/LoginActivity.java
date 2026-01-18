package com.example.vehiclerepairtracker.UI;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.vehiclerepairtracker.R;
import com.example.vehiclerepairtracker.database.Repository;
import com.example.vehiclerepairtracker.entities.User;

public class LoginActivity extends AppCompatActivity {

    Repository repository;
    EditText usernameEdit, passwordEdit;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // Initialize repository and views
        repository = new Repository(getApplication());
        usernameEdit = findViewById(R.id.usernameEdit);
        passwordEdit = findViewById(R.id.passwordEdit);
        loginButton = findViewById(R.id.loginButton);

        // Create default user
        createDefaultUser();

        // Login button click
        loginButton.setOnClickListener(v -> {
            String username = usernameEdit.getText().toString().trim();
            String password = passwordEdit.getText().toString().trim();

            if(username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Enter username and password", Toast.LENGTH_SHORT).show();
                return;
            }

            User user = repository.getUserDao().getUserByUsername(username);
            if(user != null && user.getPasswordHash().equals(hashPassword(password))) {
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, CarActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        });

        // Apply EdgeToEdge padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, ((Insets) systemBars).top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void createDefaultUser() {
        if (repository.getUserDao().getUserByUsername("admin") == null) {
            User admin = new User("admin", hashPassword("password123"));
            repository.getUserDao().insert(admin);
        }
    }

    private String hashPassword(String password) {
        // Simple reversible "hash" for demo purposes
        return new StringBuilder(password).reverse().toString();
    }
}
