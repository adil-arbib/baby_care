package com.groupe6.babycare.repositories.apis;

import com.groupe6.babycare.dtos.diaper.DiaperDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DiaperApi {

    @GET("/api/v1/diaper")
    Call<List<DiaperDTO>> getAll();

    @POST("/api/v1/diaper")
    Call<DiaperDTO> add(@Body DiaperDTO diaperDTO);

    @PUT("/api/v1/diaper/{id}")
    Call<DiaperDTO> update(@Body DiaperDTO diaperDTO, @Path("id") Long id);

    @GET("/api/v1/diaper/{id}")
    Call<DiaperDTO> getDiaperById(@Path("id") Long id);

    @DELETE("/api/v1/diaper/{id}")
    Call<Void> delete(@Path("id") Long id);
}


