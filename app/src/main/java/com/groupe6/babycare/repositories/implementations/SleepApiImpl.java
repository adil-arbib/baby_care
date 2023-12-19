package com.groupe6.babycare.repositories.implementations;

import android.content.Context;

import com.groupe6.babycare.dtos.error.ErrorDTO;
import com.groupe6.babycare.dtos.sleeping.SleepDTO;
import com.groupe6.babycare.listeners.ResponseListener;
import com.groupe6.babycare.repositories.ApiClient;
import com.groupe6.babycare.repositories.apis.SleepApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SleepApiImpl {

    private static SleepApiImpl sleepApiImpl;
    private static SleepApi sleepApi;

    private SleepApiImpl() {}

    public static SleepApiImpl getInstance(Context context) {
        if (sleepApi == null) {
            sleepApi = ApiClient.getClient(context).create(SleepApi.class);
            sleepApiImpl = new SleepApiImpl();
        }
        return sleepApiImpl;
    }

    public void getAllSleeps(final ResponseListener<List<SleepDTO>> listener) {
        sleepApi.getAll().enqueue(new Callback<List<SleepDTO>>() {
            @Override
            public void onResponse(Call<List<SleepDTO>> call, Response<List<SleepDTO>> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<List<SleepDTO>> call, Throwable t) {
                // Handle failure
            }
        });
    }

    public void createSleep(SleepDTO createRequest,final ResponseListener<SleepDTO> listener) {
        sleepApi.createSleep(createRequest).enqueue(new Callback<SleepDTO>() {
            @Override
            public void onResponse(Call<SleepDTO> call, Response<SleepDTO> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<SleepDTO> call, Throwable t) {
                // Handle failure
            }
        });
    }

    public void updateSleep(SleepDTO updateRequest, Long id, final ResponseListener<Void> listener) {
        sleepApi.updateSleep(updateRequest, id).enqueue(new Callback<Void>() {
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

    public void deleteSleep(Long id, final ResponseListener<Void> listener) {
        sleepApi.deleteSleep(id).enqueue(new Callback<Void>() {
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

    public void getSleepById(Long id, final ResponseListener<SleepDTO> listener) {
        sleepApi.getSleepById(id).enqueue(new Callback<SleepDTO>() {
            @Override
            public void onResponse(Call<SleepDTO> call, Response<SleepDTO> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<SleepDTO> call, Throwable t) {
                // Handle failure
            }
        });
    }
}
