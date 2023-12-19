package com.groupe6.babycare.repositories.implementations;

import android.content.Context;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.groupe6.babycare.dtos.activities.ActivityCreateDTO;
import com.groupe6.babycare.dtos.activities.ActivityDTO;
import com.groupe6.babycare.dtos.activities.SimpleActivityDTO;
import com.groupe6.babycare.dtos.auth.TokenResponse;
import com.groupe6.babycare.dtos.error.ErrorDTO;
import com.groupe6.babycare.listeners.ResponseListener;
import com.groupe6.babycare.repositories.ApiClient;
import com.groupe6.babycare.repositories.apis.ActivityApi;

//TODO : Handle failure in all implementations
public class ActivityApiImpl {

    private static ActivityApiImpl activityApiImpl;
    private static ActivityApi activityApi;

    private ActivityApiImpl(){}

    public static ActivityApiImpl getInstance(Context context) {
        if (activityApi == null) {
            activityApi = ApiClient.getClient(context).create(ActivityApi.class);
            activityApiImpl = new ActivityApiImpl();
        }
        return activityApiImpl;
    }

    public void getAllActivities(final ResponseListener<TokenResponse> listener) {
        activityApi.getAll().enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if(response.isSuccessful()) listener.onSuccess(response.body());
                else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
            }
        });
    }

    public void addActivity(ActivityCreateDTO activityCreateDTO, final ResponseListener<ActivityCreateDTO> listener) {
        activityApi.addActivity(activityCreateDTO).enqueue(new Callback<ActivityCreateDTO>() {
            @Override
            public void onResponse(Call<ActivityCreateDTO> call, Response<ActivityCreateDTO> response) {
                if(response.isSuccessful()) listener.onSuccess(response.body());
                else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<ActivityCreateDTO> call, Throwable t) {
            }
        });
    }

    public void updateActivity(ActivityDTO activityDTO, Long id, final ResponseListener<TokenResponse> listener) {
        activityApi.updateActivity(activityDTO, id).enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if(response.isSuccessful()) listener.onSuccess(response.body());
                else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
            }
        });
    }

    public void getActivityById(Long id, final ResponseListener<TokenResponse> listener) {
        activityApi.getActivityById(id).enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if(response.isSuccessful()) listener.onSuccess(response.body());
                else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
            }
        });
    }

    public void deleteActivity(Long id, final ResponseListener<TokenResponse> listener) {
        activityApi.delete(id).enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if(response.isSuccessful()) listener.onSuccess(response.body());
                else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
            }
        });
    }


}
