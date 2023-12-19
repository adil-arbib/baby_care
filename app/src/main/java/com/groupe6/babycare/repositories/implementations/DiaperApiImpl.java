package com.groupe6.babycare.repositories.implementations;


import android.content.Context;

import com.groupe6.babycare.dtos.diaper.DiaperCreateDTO;
import com.groupe6.babycare.dtos.diaper.DiaperDTO;
import com.groupe6.babycare.dtos.error.ErrorDTO;
import com.groupe6.babycare.listeners.ResponseListener;
import com.groupe6.babycare.repositories.ApiClient;
import com.groupe6.babycare.repositories.apis.DiaperApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiaperApiImpl {

    private static DiaperApiImpl diaperApiImpl;
    private static DiaperApi diaperApi;

    private DiaperApiImpl() {}

    public static DiaperApiImpl getInstance(Context context) {
        if (diaperApi == null) {
            diaperApi = ApiClient.getClient(context).create(DiaperApi.class);
            diaperApiImpl = new DiaperApiImpl();
        }
        return diaperApiImpl;
    }

    public void getAllDiapers(final ResponseListener<List<DiaperDTO>> listener) {
        diaperApi.getAll().enqueue(new Callback<List<DiaperDTO>>() {
            @Override
            public void onResponse(Call<List<DiaperDTO>> call, Response<List<DiaperDTO>> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<List<DiaperDTO>> call, Throwable t) {

            }
        });
    }

    public void addDiaper(DiaperCreateDTO diaperDTO, final ResponseListener<DiaperCreateDTO> listener) {
        diaperApi.add(diaperDTO).enqueue(new Callback<DiaperCreateDTO>() {
            @Override
            public void onResponse(Call<DiaperCreateDTO> call, Response<DiaperCreateDTO> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<DiaperCreateDTO> call, Throwable t) {

            }
        });
    }

    public void updateDiaper(DiaperDTO diaperDTO, Long id, final ResponseListener<DiaperDTO> listener) {
        diaperApi.update(diaperDTO, id).enqueue(new Callback<DiaperDTO>() {
            @Override
            public void onResponse(Call<DiaperDTO> call, Response<DiaperDTO> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<DiaperDTO> call, Throwable t) {

            }
        });
    }

    public void getDiaperById(Long id, final ResponseListener<DiaperDTO> listener) {
        diaperApi.getDiaperById(id).enqueue(new Callback<DiaperDTO>() {
            @Override
            public void onResponse(Call<DiaperDTO> call, Response<DiaperDTO> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<DiaperDTO> call, Throwable t) {

            }
        });
    }

    public void deleteDiaper(Long id, final ResponseListener<Void> listener) {
        diaperApi.delete(id).enqueue(new Callback<Void>() {
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

            }
        });
    }
}