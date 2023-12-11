package com.groupe6.babycare.repositories.apis;

import com.groupe6.babycare.dtos.children.ChildDTO;
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
    Call<List<ChildDTO>> getAll();

    @POST("/api/v1/children")
    Call<ChildDTO> add(@Body ChildDTO childDTO);

    @PUT("/api/v1/children/{id}/update")
    Call<ChildDTO> update(@Body ChildDTO childDTO, @Path("id") Long id);

    @GET("/api/v1/children/{id}")
    Call<ChildDTO> getChildById(@Path("id") Long id);

    @DELETE("/api/v1/children/{id}")
    Call<Void> delete(@Path("id") Long id);
}
