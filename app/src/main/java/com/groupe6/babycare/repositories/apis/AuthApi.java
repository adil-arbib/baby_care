package com.groupe6.babycare.repositories.apis;

import com.groupe6.babycare.dtos.auth.LoginRequest;
import com.groupe6.babycare.dtos.auth.RegisterRequest;
import com.groupe6.babycare.dtos.auth.TokenResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AuthApi {

    @POST("/api/v1/auth/authenticate")
    Call<TokenResponse> login(@Body LoginRequest loginRequest);




}
