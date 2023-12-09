package com.groupe6.babycare.repositories.apis;

import com.groupe6.babycare.dtos.notes.NoteDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface NoteApi {

    @GET("/api/v1/note")
    Call<List<NoteDTO>> getAll();

    @POST("/api/v1/note/add")
    Call<NoteDTO> createNote(@Body NoteDTO createRequest);

    @PUT("/api/v1/note/{id}/update")
    Call<NoteDTO> updateNote(@Body NoteDTO updateRequest, @Path("id") Long id);

    @GET("/api/v1/note/{id}")
    Call<NoteDTO> getNoteById(@Path("id") Long id);

    @DELETE("/api/v1/note/delete/{id}")
    Call<Void> deleteNote(@Path("id") Long id);
}