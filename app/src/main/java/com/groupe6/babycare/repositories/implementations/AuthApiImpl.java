package com.groupe6.babycare.repositories.implementations;

import android.content.Context;

import com.groupe6.babycare.dtos.auth.LoginRequest;
import com.groupe6.babycare.dtos.auth.RegisterRequest;
import com.groupe6.babycare.dtos.auth.TokenResponse;
import com.groupe6.babycare.dtos.error.ErrorDTO;
import com.groupe6.babycare.listeners.ResponseListener;
import com.groupe6.babycare.repositories.ApiClient;
import com.groupe6.babycare.repositories.PublicApiClient;
import com.groupe6.babycare.repositories.apis.AuthApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthApiImpl {

    private static AuthApiImpl authApiImpl;

    private static AuthApi authApi;

    private AuthApiImpl(){}

    public static AuthApiImpl getInstance(Context context) {
        if(authApi == null) {
            authApi = PublicApiClient.getClient().create(AuthApi.class);
            authApiImpl = new AuthApiImpl();
        }
        return authApiImpl;
    }


    public void login(LoginRequest loginRequest, final ResponseListener<TokenResponse> listener) {
        authApi.login(loginRequest).enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if(response.isSuccessful()) listener.onSuccess(response.body());
                else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
            }
        });
    }


}
