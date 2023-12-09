package com.groupe6.babycare.repositories.implementations;

import android.content.Context;

import com.groupe6.babycare.dtos.feeding.FoodDTO;
import com.groupe6.babycare.dtos.error.ErrorDTO;
import com.groupe6.babycare.listeners.ResponseListener;
import com.groupe6.babycare.repositories.ApiClient;
import com.groupe6.babycare.repositories.apis.FoodApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodApiImpl {

    private static FoodApiImpl foodApiImpl;
    private static FoodApi foodApi;

    private FoodApiImpl() {
    }

    public static FoodApiImpl getInstance(Context context) {
        if (foodApi == null) {
            foodApi = ApiClient.getClient(context).create(FoodApi.class);
            foodApiImpl = new FoodApiImpl();
        }
        return foodApiImpl;
    }

    public void getAllFoods(final ResponseListener<List<FoodDTO>> listener) {
        foodApi.getAll().enqueue(new Callback<List<FoodDTO>>() {
            @Override
            public void onResponse(Call<List<FoodDTO>> call, Response<List<FoodDTO>> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<List<FoodDTO>> call, Throwable t) {
                // Handle failure
            }
        });
    }

    public void createFood(FoodDTO createRequest, final ResponseListener<FoodDTO> listener) {
        foodApi.createFood(createRequest).enqueue(new Callback<FoodDTO>() {
            @Override
            public void onResponse(Call<FoodDTO> call, Response<FoodDTO> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<FoodDTO> call, Throwable t) {
                // Handle failure
            }
        });
    }

    public void updateFood(FoodDTO updateRequest, Long id, final ResponseListener<FoodDTO> listener) {
        foodApi.updateFood(updateRequest, id).enqueue(new Callback<FoodDTO>() {
            @Override
            public void onResponse(Call<FoodDTO> call, Response<FoodDTO> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<FoodDTO> call, Throwable t) {
                // Handle failure
            }
        });
    }

    public void getFood(Long id, final ResponseListener<FoodDTO> listener) {
        foodApi.getFood(id).enqueue(new Callback<FoodDTO>() {
            @Override
            public void onResponse(Call<FoodDTO> call, Response<FoodDTO> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<FoodDTO> call, Throwable t) {
                // Handle failure
            }
        });
    }

    public void deleteFood(Long id, final ResponseListener<Void> listener) {
        foodApi.deleteFood(id).enqueue(new Callback<Void>() {
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
