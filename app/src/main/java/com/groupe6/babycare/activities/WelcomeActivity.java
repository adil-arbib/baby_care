package com.groupe6.babycare.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.groupe6.babycare.R;
import com.groupe6.babycare.databinding.ActivityWelcomeBinding;
import com.groupe6.babycare.utils.TokenManager;

public class WelcomeActivity extends AppCompatActivity {

    private ActivityWelcomeBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        checkLogin();

        binding.btnSignIn.setOnClickListener(v -> {
            startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
        });

        binding.btnSignUp.setOnClickListener(v -> {
            startActivity(new Intent(WelcomeActivity.this, RegisterActivity.class));
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void checkLogin() {
        TokenManager tokenManager = new TokenManager(this);
        if(tokenManager.isLoggedIn() && !tokenManager.tokenNotExpired()) {
            tokenManager.clearToken();
        }else if(tokenManager.tokenNotExpired()) {
            startActivity(new Intent(WelcomeActivity.this, SelectChildActivity.class));
        }

    }
}