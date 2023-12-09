package com.groupe6.babycare.repositories.apis;

import com.groupe6.babycare.dtos.children.ChildRequestDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ChildApi {

    @GET("/api/v1/children")
    Call<List<ChildRequestDTO>> getAll();

    @POST("/api/v1/children")
    Call<ChildRequestDTO> add(@Body ChildRequestDTO childRequestDTO);

    @PUT("/api/v1/children/{id}")
    Call<ChildRequestDTO> update(@Body ChildRequestDTO childRequestDTO, @Path("id") Long id);

    @GET("/api/v1/children/{id}")
    Call<ChildRequestDTO> getChildById(@Path("id") Long id);

    @DELETE("/api/v1/children/{id}")
    Call<Void> delete(@Path("id") Long id);
}
