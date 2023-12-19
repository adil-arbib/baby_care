package com.groupe6.babycare.repositories.apis;

import com.groupe6.babycare.dtos.activities.ActivityCreateDTO;
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

    @POST("/api/v1/activity")
    Call<ActivityCreateDTO> addActivity(@Body ActivityCreateDTO activityCreateDTO);

    @PUT("/api/v1/activity/{id}")
    Call<ActivityDTO> updateActivity(@Body ActivityDTO activityDTO, @Path("id") Long id);

    @GET("/api/v1/activity/{id}")
    Call<ActivityDTO> getActivityById(@Path("id") Long id);

    @DELETE("/api/v1/activity/{id}")
    Call<Void> delete(@Path("id") Long id);

}
