package com.groupe6.babycare.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.groupe6.babycare.R;
import com.groupe6.babycare.databinding.ActivityLoginBinding;
import com.groupe6.babycare.databinding.ActivityWelcomeBinding;
import com.groupe6.babycare.dtos.auth.LoginRequest;
import com.groupe6.babycare.dtos.auth.TokenResponse;
import com.groupe6.babycare.dtos.error.ErrorDTO;
import com.groupe6.babycare.listeners.ResponseListener;
import com.groupe6.babycare.repositories.implementations.AuthApiImpl;
import com.groupe6.babycare.utils.InputsUtils;
import com.groupe6.babycare.utils.TokenManager;

public class LoginActivity extends AppCompatActivity {


    private ActivityLoginBinding binding;

    private AuthApiImpl authApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        authApi = AuthApiImpl.getInstance(this);

        binding.txtSignUp.setOnClickListener((e) -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
        

        binding.btnSignIn.setOnClickListener(v -> {
            if(!InputsUtils.validateInputs(binding.inputEmail, binding.inputPassword))return;
            binding.progressBar.setVisibility(View.VISIBLE);
            binding.btnSignIn.setEnabled(false);
            LoginRequest loginRequest = new LoginRequest(binding.inputEmail.getText().toString(),binding.inputPassword.getText().toString() );

            authApi.login(loginRequest, new ResponseListener<TokenResponse>() {
                @Override
                public void onSuccess(TokenResponse response) {
                    binding.progressBar.setVisibility(View.GONE);
                    binding.btnSignIn.setEnabled(true);
                    TokenManager tokenManager = new TokenManager(getApplicationContext());
                    tokenManager.saveToken(response.getTokon());
                    tokenManager.storeCredentials(response.getTokon());
                    startActivity(new Intent(LoginActivity.this, SelectChildActivity.class));


                }

                @Override
                public void onError(ErrorDTO error) {
                    binding.progressBar.setVisibility(View.GONE);
                    binding.btnSignIn.setEnabled(true);
                    binding.inpPassword.setError("Password is incorrect !!");
                }
            });

        });
    }




}