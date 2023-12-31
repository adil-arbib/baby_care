package com.groupe6.babycare.repositories.implementations;


import android.content.Context;

import com.groupe6.babycare.dtos.activities.ActivityDTO;
import com.groupe6.babycare.dtos.children.ChildDTO;
import com.groupe6.babycare.dtos.diaper.DiaperDTO;
import com.groupe6.babycare.dtos.error.ErrorDTO;
import com.groupe6.babycare.dtos.feeding.FoodDTO;
import com.groupe6.babycare.dtos.healthcare.HealthCareDTO;
import com.groupe6.babycare.dtos.sleeping.SleepDTO;
import com.groupe6.babycare.dtos.todayInfo.TodayInfo;
import com.groupe6.babycare.listeners.ResponseListener;
import com.groupe6.babycare.repositories.ApiClient;
import com.groupe6.babycare.repositories.apis.ChildApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class ChildApiImpl {

    private static ChildApiImpl childApiImpl;
    private static ChildApi childApi;

    private ChildApiImpl(){}

    public static ChildApiImpl getInstance(Context context) {
        if (childApi == null) {
            childApi = ApiClient.getClient(context).create(ChildApi.class);
            childApiImpl = new ChildApiImpl();
        }
        return childApiImpl;
    }

    public void getAllChildren(final ResponseListener<List<ChildDTO>> listener) {
        childApi.getAll().enqueue(new Callback<List<ChildDTO>>() {
            @Override
            public void onResponse(Call<List<ChildDTO>> call, Response<List<ChildDTO>> response) {
                if(response.isSuccessful()) listener.onSuccess(response.body());
                else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<List<ChildDTO>> call, Throwable t) {
            }
        });
    }

    public void addChild(ChildDTO childRequestDTO, final ResponseListener<ChildDTO> listener) {
        childApi.add(childRequestDTO).enqueue(new Callback<ChildDTO>() {
            @Override
            public void onResponse(Call<ChildDTO> call, Response<ChildDTO> response) {
                if(response.isSuccessful()) listener.onSuccess(response.body());
                else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<ChildDTO> call, Throwable t) {
                // Handle failure
            }
        });
    }

    public void updateChild(ChildDTO childRequestDTO, Long id, final ResponseListener<ChildDTO> listener) {
        childApi.update(childRequestDTO, id).enqueue(new Callback<ChildDTO>() {
            @Override
            public void onResponse(Call<ChildDTO> call, Response<ChildDTO> response) {
                if(response.isSuccessful()) listener.onSuccess(response.body());
                else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<ChildDTO> call, Throwable t) {
                // Handle failure
            }
        });
    }

    public void getChildById(Long id, final ResponseListener<ChildDTO> listener) {
        childApi.getChildById(id).enqueue(new Callback<ChildDTO>() {
            @Override
            public void onResponse(Call<ChildDTO> call, Response<ChildDTO> response) {
                if(response.isSuccessful()) listener.onSuccess(response.body());
                else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<ChildDTO> call, Throwable t) {
                // Handle failure
            }
        });
    }

    public void deleteChild(Long id, final ResponseListener<Void> listener) {
        childApi.delete(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()) listener.onSuccess(null);
                else {
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


   public void getChildNutrition(Long childId, final ResponseListener<List<FoodDTO>> listener) {
         childApi.getChildNutritions(childId).enqueue(new Callback<List<FoodDTO>>() {
            @Override
            public void onResponse(Call<List<FoodDTO>> call, Response<List<FoodDTO>> response) {
                if(response.isSuccessful()) listener.onSuccess(response.body());
                else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<List<FoodDTO>> call, Throwable t) {

            }
        });
    }

    public void getChildSleep(Long childId, final ResponseListener<List<SleepDTO>> listener) {
        childApi.getChildSleep(childId).enqueue(new Callback<List<SleepDTO>>() {
            @Override
            public void onResponse(Call<List<SleepDTO>> call, Response<List<SleepDTO>> response) {
                if(response.isSuccessful()) listener.onSuccess(response.body());
                else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<List<SleepDTO>> call, Throwable t) {

            }
        });
    }


    public void getChildDiaper(Long childId, final ResponseListener<List<DiaperDTO>> listener) {
        childApi.getChildDiaper(childId).enqueue(new Callback<List<DiaperDTO>>() {
            @Override
            public void onResponse(Call<List<DiaperDTO>> call, Response<List<DiaperDTO>> response) {
                if(response.isSuccessful()) listener.onSuccess(response.body());
                else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<List<DiaperDTO>> call, Throwable t) {

            }
        });
    }

    public void getChildActivities(Long childId, final ResponseListener<List<ActivityDTO>> listener) {
        childApi.getChildActivities(childId).enqueue(new Callback<List<ActivityDTO>>() {
            @Override
            public void onResponse(Call<List<ActivityDTO>> call, Response<List<ActivityDTO>> response) {
                if(response.isSuccessful()) listener.onSuccess(response.body());
                else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<List<ActivityDTO>> call, Throwable t) {

            }
        });
    }

    public void getChildHealthCare(Long childId, final ResponseListener<List<HealthCareDTO>> listener) {
        childApi.getChildHealthCare(childId).enqueue(new Callback<List<HealthCareDTO>>() {
            @Override
            public void onResponse(Call<List<HealthCareDTO>> call, Response<List<HealthCareDTO>> response) {
                if(response.isSuccessful()) listener.onSuccess(response.body());
                else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<List<HealthCareDTO>> call, Throwable t) {

            }
        });
    }

    public void todayInfo(Long childId, final ResponseListener<TodayInfo> listener) {
        childApi.todayInfo(childId).enqueue(new Callback<TodayInfo>() {
            @Override
            public void onResponse(Call<TodayInfo> call, Response<TodayInfo> response) {
                if(response.isSuccessful()) listener.onSuccess(response.body());
                else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<TodayInfo> call, Throwable t) {

            }
        });
    }
}
