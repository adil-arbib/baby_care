package com.groupe6.babycare.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.groupe6.babycare.R;
import com.groupe6.babycare.databinding.ActivityRegisterBinding;
import com.groupe6.babycare.dtos.auth.RegisterRequest;
import com.groupe6.babycare.dtos.auth.TokenResponse;
import com.groupe6.babycare.dtos.error.ErrorDTO;
import com.groupe6.babycare.listeners.ResponseListener;
import com.groupe6.babycare.repositories.implementations.AuthApiImpl;
import com.groupe6.babycare.utils.InputsUtils;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.txtSignIn.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        });

        binding.btnSignUp.setOnClickListener(v -> {
            if(!InputsUtils.validateInputs(binding.inputFirstname,binding.inputLastname, binding.inputEmail,
                    binding.inputPassword, binding.inputConfPassword))
                return;
            if(!binding.inputPassword.getText().toString().equals(binding.inputConfPassword.getText().toString())){
                binding.inpConfPassword.setError("Passwords don't match");
                return;
            }
            String gender = binding.radioFather.isSelected() ?
                    "MALE" : "FEMALE";
            RegisterRequest registerRequest = new RegisterRequest(
                    binding.inputFirstname.getText().toString(),
                    binding.inputLastname.getText().toString(),
                    binding.inputEmail.getText().toString(),
                    binding.inputPassword.getText().toString(),
                    gender);

            AuthApiImpl authApi = AuthApiImpl.getInstance(this);
            authApi.register(registerRequest, new ResponseListener<TokenResponse>() {
                @Override
                public void onSuccess(TokenResponse response) {
                    Toast.makeText(RegisterActivity.this, "register Successfully !!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(ErrorDTO error) {
                    Toast.makeText(RegisterActivity.this, "An error was occurred !!", Toast.LENGTH_SHORT).show();

                }
            });

        });

    }
}