package com.groupe6.babycare.repositories.apis;

import com.groupe6.babycare.dtos.activities.ActivityDTO;
import com.groupe6.babycare.dtos.children.ChildDTO;
import com.groupe6.babycare.dtos.diaper.DiaperDTO;
import com.groupe6.babycare.dtos.feeding.FoodDTO;
import com.groupe6.babycare.dtos.sleeping.SleepDTO;

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

    @POST("/api/v1/children/add")
    Call<ChildDTO> add(@Body ChildDTO childDTO);

    @PUT("/api/v1/children/{id}/update")
    Call<ChildDTO> update(@Body ChildDTO childDTO, @Path("id") Long id);

    @GET("/api/v1/children/{id}")
    Call<ChildDTO> getChildById(@Path("id") Long id);

    @DELETE("/api/v1/children/{id}/delete")
    Call<Void> delete(@Path("id") Long id);

    @GET("/api/v1/children/{id}/nutritions")
    Call<List<FoodDTO>> getChildNutritions(@Path("id") Long id);

    @GET("/api/v1/children/{id}/sleep")
    Call<List<SleepDTO>> getChildSleep(@Path("id") Long id);

    @GET("/api/v1/children/{id}/diaper")
    Call<List<DiaperDTO>> getChildDiaper(@Path("id") Long id);

    @GET("/api/v1/children/{id}/activities")
    Call<List<ActivityDTO>> getChildActivities(@Path("id") Long id);
}
