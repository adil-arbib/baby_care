package com.groupe6.babycare.repositories.implementations;

import android.content.Context;

import com.groupe6.babycare.dtos.children.ChildDTO;
import com.groupe6.babycare.dtos.error.ErrorDTO;
import com.groupe6.babycare.listeners.ResponseListener;
import com.groupe6.babycare.repositories.ApiClient;
import com.groupe6.babycare.repositories.PublicApiClient;
import com.groupe6.babycare.repositories.apis.AuthApi;
import com.groupe6.babycare.repositories.apis.ParentApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ParentApiImpl {

    private static ParentApiImpl parentApiImpl;

    private static ParentApi parentApi;

    private ParentApiImpl(){}

    public static ParentApiImpl getInstance(Context context) {
        if(parentApi == null) {
            parentApi = ApiClient.getClient(context).create(ParentApi.class);
            parentApiImpl = new ParentApiImpl();
        }
        return parentApiImpl;
    }

    public void getChildren(Long parentId, final ResponseListener<List<ChildDTO>> responseListener) {
        parentApi.getChildren(parentId).enqueue(new Callback<List<ChildDTO>>() {
            @Override
            public void onResponse(Call<List<ChildDTO>> call, Response<List<ChildDTO>> response) {
                if(response.isSuccessful()) responseListener.onSuccess(response.body());
                else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    responseListener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<List<ChildDTO>> call, Throwable t) {

            }
        });
    }

}
