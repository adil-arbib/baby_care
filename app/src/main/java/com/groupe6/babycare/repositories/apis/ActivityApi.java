package com.groupe6.babycare.repositories.apis;

import com.groupe6.babycare.dtos.activities.ActivityDTO;
import com.groupe6.babycare.dtos.activities.SimpleActivityDTO;
import com.groupe6.babycare.dtos.auth.TokenResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ActivityApi {
    @GET("/api/v1/activity")
    Call<TokenResponse> getAll();

    @POST("/api/v1/activity/")
    Call<TokenResponse> addActivity(@Body SimpleActivityDTO simpleActivityDTO);

    @PUT("/api/v1/activity/{id}")
    Call<TokenResponse> updateActivity(@Body ActivityDTO activityDTO, @Path("id") Long id);

    @GET("/api/v1/activity/{id}")
    Call<TokenResponse> getActivityById(@Path("id") Long id);

    @DELETE("/api/v1/activity/{id}")
    Call<TokenResponse> delete(@Path("id") Long id);

}
