package com.groupe6.babycare.repositories.apis;

import com.groupe6.babycare.dtos.feeding.FoodDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import java.util.List;

public interface FoodApi {

    @GET("/api/v1/nutrition")
    Call<List<FoodDTO>> getAll();

    @POST("/api/v1/nutrition/add")
    Call<FoodDTO> createFood(@Body FoodDTO createRequest);

    @PUT("/api/v1/nutrition/{id}/update")
    Call<FoodDTO> updateFood(@Body FoodDTO updateRequest, @Path("id") Long id);

    @GET("/api/v1/nutrition/{id}")
    Call<FoodDTO> getFood(@Path("id") Long id);

    @DELETE("/api/v1/nutrition/delete/{id}")
    Call<Void> deleteFood(@Path("id") Long id);
}
