package com.groupe6.babycare.repositories.apis;

import com.groupe6.babycare.dtos.tips.TipsDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TipsApi {

// TODO: Add all methods for TipsApi
    @GET("/api/v1/tips")
    Call<List<TipsDTO>> getAll();

    @GET("/api/v1/tips/{id}")
    Call<TipsDTO> getNoteById(@Path("id") Long id);
}
