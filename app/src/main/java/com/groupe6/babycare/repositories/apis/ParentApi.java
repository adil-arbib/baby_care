package com.groupe6.babycare.repositories.apis;

import com.groupe6.babycare.dtos.children.ChildDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ParentApi {

    @GET("/api/v1/users/{parentId}/children")
    Call<List<ChildDTO>> getChildren(@Path("parentId") Long parentId);


    @POST("/api/v1/users/{id}/children")
    Call<Void> addChild(@Body ChildDTO child, @Path("id") Long id);
}
