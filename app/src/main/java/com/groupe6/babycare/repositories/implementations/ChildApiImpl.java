package com.groupe6.babycare.repositories.implementations;


import android.content.Context;

import com.groupe6.babycare.dtos.children.ChildRequestDTO;
import com.groupe6.babycare.dtos.error.ErrorDTO;
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

    public void getAllChildren(final ResponseListener<List<ChildRequestDTO>> listener) {
        childApi.getAll().enqueue(new Callback<List<ChildRequestDTO>>() {
            @Override
            public void onResponse(Call<List<ChildRequestDTO>> call, Response<List<ChildRequestDTO>> response) {
                if(response.isSuccessful()) listener.onSuccess(response.body());
                else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<List<ChildRequestDTO>> call, Throwable t) {
            }
        });
    }

    public void addChild(ChildRequestDTO childRequestDTO, final ResponseListener<ChildRequestDTO> listener) {
        childApi.add(childRequestDTO).enqueue(new Callback<ChildRequestDTO>() {
            @Override
            public void onResponse(Call<ChildRequestDTO> call, Response<ChildRequestDTO> response) {
                if(response.isSuccessful()) listener.onSuccess(response.body());
                else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<ChildRequestDTO> call, Throwable t) {
                // Handle failure
            }
        });
    }

    public void updateChild(ChildRequestDTO childRequestDTO, Long id, final ResponseListener<ChildRequestDTO> listener) {
        childApi.update(childRequestDTO, id).enqueue(new Callback<ChildRequestDTO>() {
            @Override
            public void onResponse(Call<ChildRequestDTO> call, Response<ChildRequestDTO> response) {
                if(response.isSuccessful()) listener.onSuccess(response.body());
                else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<ChildRequestDTO> call, Throwable t) {
                // Handle failure
            }
        });
    }

    public void getChildById(Long id, final ResponseListener<ChildRequestDTO> listener) {
        childApi.getChildById(id).enqueue(new Callback<ChildRequestDTO>() {
            @Override
            public void onResponse(Call<ChildRequestDTO> call, Response<ChildRequestDTO> response) {
                if(response.isSuccessful()) listener.onSuccess(response.body());
                else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<ChildRequestDTO> call, Throwable t) {
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
}
