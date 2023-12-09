package com.groupe6.babycare.repositories.apis;

import com.groupe6.babycare.dtos.healthcare.HealthCareDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface HealthCareApi {

    @GET("/api/v1/healthCare")
    Call<List<HealthCareDTO>> getAll();

    @POST("/api/v1/healthCare/add")
    Call<HealthCareDTO> createHealth(@Body HealthCareDTO createRequest);

    @PUT("/api/v1/healthCare/{id}/update")
    Call<HealthCareDTO> updateHealthCare(@Body HealthCareDTO updateRequest, @Path("id") Long id);

    @GET("/api/v1/healthCare/{id}")
    Call<HealthCareDTO> getHealthCare(@Path("id") Long id);

    @DELETE("/api/v1/healthCare/delete/{id}")
    Call<Void> deleteHealthCare(@Path("id") Long id);
}