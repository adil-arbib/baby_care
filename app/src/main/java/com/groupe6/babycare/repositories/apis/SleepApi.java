package com.groupe6.babycare.repositories.apis;
import com.groupe6.babycare.dtos.sleeping.SleepDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SleepApi {

    @GET("/api/v1/sleep")
    Call<List<SleepDTO>> getAll();

    @POST("/api/v1/sleep")
    Call<SleepDTO> createSleep(@Body SleepDTO createRequest);

    @PUT("/api/v1/sleep/{id}")
    Call<Void> updateSleep(@Body SleepDTO updateRequest, @Path("id") Long id);

    @DELETE("/api/v1/sleep/{id}")
    Call<Void> deleteSleep(@Path("id") Long id);

    @GET("/api/v1/sleep/{id}")
    Call<SleepDTO> getSleepById(@Path("id") Long id);
}
