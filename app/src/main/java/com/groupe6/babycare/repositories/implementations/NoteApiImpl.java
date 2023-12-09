package com.groupe6.babycare.repositories.implementations;

import android.content.Context;

import com.groupe6.babycare.dtos.error.ErrorDTO;
import com.groupe6.babycare.dtos.notes.NoteDTO;
import com.groupe6.babycare.listeners.ResponseListener;
import com.groupe6.babycare.repositories.ApiClient;
import com.groupe6.babycare.repositories.apis.NoteApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoteApiImpl {

    private static NoteApiImpl noteApiImpl;
    private static NoteApi noteApi;

    private NoteApiImpl() {}

    public static NoteApiImpl getInstance(Context context) {
        if (noteApi == null) {
            noteApi = ApiClient.getClient(context).create(NoteApi.class);
            noteApiImpl = new NoteApiImpl();
        }
        return noteApiImpl;
    }

    public void getAllNotes(final ResponseListener<List<NoteDTO>> listener) {
        noteApi.getAll().enqueue(new Callback<List<NoteDTO>>() {
            @Override
            public void onResponse(Call<List<NoteDTO>> call, Response<List<NoteDTO>> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<List<NoteDTO>> call, Throwable t) {
                // Handle failure
            }
        });
    }

    public void createNote(NoteDTO createRequest, final ResponseListener<NoteDTO> listener) {
        noteApi.createNote(createRequest).enqueue(new Callback<NoteDTO>() {
            @Override
            public void onResponse(Call<NoteDTO> call, Response<NoteDTO> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<NoteDTO> call, Throwable t) {
                // Handle failure
            }
        });
    }

    public void updateNote(NoteDTO updateRequest, Long id, final ResponseListener<NoteDTO> listener) {
        noteApi.updateNote(updateRequest, id).enqueue(new Callback<NoteDTO>() {
            @Override
            public void onResponse(Call<NoteDTO> call, Response<NoteDTO> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<NoteDTO> call, Throwable t) {
                // Handle failure
            }
        });
    }

    public void getNoteById(Long id, final ResponseListener<NoteDTO> listener) {
        noteApi.getNoteById(id).enqueue(new Callback<NoteDTO>() {
            @Override
            public void onResponse(Call<NoteDTO> call, Response<NoteDTO> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<NoteDTO> call, Throwable t) {
                // Handle failure
            }
        });
    }

    public void deleteNote(Long id, final ResponseListener<Void> listener) {
        noteApi.deleteNote(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(null);
                } else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Handle failure
            }
        });
    }
}
