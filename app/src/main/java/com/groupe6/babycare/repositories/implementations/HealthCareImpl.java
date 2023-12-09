package com.groupe6.babycare.repositories.implementations;
import android.content.Context;

import com.groupe6.babycare.dtos.error.ErrorDTO;
import com.groupe6.babycare.dtos.healthcare.HealthCareDTO;
import com.groupe6.babycare.listeners.ResponseListener;
import com.groupe6.babycare.repositories.ApiClient;
import com.groupe6.babycare.repositories.apis.HealthCareApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class HealthCareImpl {


    private static HealthCareImpl healthCare;
    private static HealthCareApi healthCareApi;

    private HealthCareImpl() {
    }

    public static HealthCareImpl getInstance(Context context) {
        if (healthCareApi == null) {
            healthCareApi = ApiClient.getClient(context).create(HealthCareApi.class);
            healthCare = new HealthCareImpl();
        }
        return healthCare;
    }


    public void getAllHealthCare(final ResponseListener<List<HealthCareDTO>> listener) {
        healthCareApi.getAll().enqueue(new Callback<List<HealthCareDTO>>() {
            @Override
            public void onResponse(Call<List<HealthCareDTO>> call, Response<List<HealthCareDTO>> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<List<HealthCareDTO>> call, Throwable t) {
                // Handle failure
            }
        });
    }


    public void createHealthCare(HealthCareDTO createRequest, final ResponseListener<HealthCareDTO> listener) {
        healthCareApi.createHealth(createRequest).enqueue(new Callback<HealthCareDTO>() {
            @Override
            public void onResponse(Call<HealthCareDTO> call, Response<HealthCareDTO> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<HealthCareDTO> call, Throwable t) {
                // Handle failure
            }
        });
    }

    public void updateHealthCare(HealthCareDTO updateRequest, Long id, final ResponseListener<HealthCareDTO> listener) {
        healthCareApi.updateHealthCare(updateRequest, id).enqueue(new Callback<HealthCareDTO>() {
            @Override
            public void onResponse(Call<HealthCareDTO> call, Response<HealthCareDTO> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<HealthCareDTO> call, Throwable t) {
                // Handle failure
            }
        });
    }

    public void getFood(Long id, final ResponseListener<HealthCareDTO> listener) {
        healthCareApi.getHealthCare(id).enqueue(new Callback<HealthCareDTO>() {
            @Override
            public void onResponse(Call<HealthCareDTO> call, Response<HealthCareDTO> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<HealthCareDTO> call, Throwable t) {
                // Handle failure
            }
        });
    }

    public void deleteHealthCare(Long id, final ResponseListener<Void> listener) {
        healthCareApi.deleteHealthCare(id).enqueue(new Callback<Void>() {
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
