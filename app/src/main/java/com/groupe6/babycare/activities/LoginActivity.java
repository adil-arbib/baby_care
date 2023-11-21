package com.groupe6.babycare.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.groupe6.babycare.R;
import com.groupe6.babycare.databinding.ActivityLoginBinding;
import com.groupe6.babycare.databinding.ActivityWelcomeBinding;

public class LoginActivity extends AppCompatActivity {


    private ActivityLoginBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.txtSignUp.setOnClickListener((e) -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });


        binding.btnSignIn.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, SelectChildActivity.class));
        });
    }
}