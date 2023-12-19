package com.groupe6.babycare.repositories.implementations;

import android.content.Context;

import com.groupe6.babycare.dtos.error.ErrorDTO;
import com.groupe6.babycare.dtos.tips.TipsDTO;
import com.groupe6.babycare.listeners.ResponseListener;
import com.groupe6.babycare.repositories.ApiClient;
import com.groupe6.babycare.repositories.apis.TipsApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TipsApiImpl {

    // TODO: Add implementation for TipsApi
    private static TipsApiImpl tipsApiImpl;
    private static TipsApi tipsApi;

    public TipsApiImpl() {
    }

    public static TipsApiImpl getInstance(Context context) {
        if (tipsApi == null) {
            tipsApi = ApiClient.getClient(context).create(TipsApi.class);
            tipsApiImpl = new TipsApiImpl();
        }
        return tipsApiImpl;
    }


      public void getAllTips(final ResponseListener<List<TipsDTO>> listener) {
        tipsApi.getAll().enqueue(new Callback<List<TipsDTO>>() {
            @Override
            public void onResponse(Call<List<TipsDTO>> call, Response<List<TipsDTO>> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }
            @Override
            public void onFailure(Call<List<TipsDTO>> call, Throwable t) {
                // Handle failure
            }
        });
    }

    public void getTip(Long id, final  ResponseListener<TipsDTO> listener){
        tipsApi.getNoteById(id).enqueue(new Callback<TipsDTO>() {
            @Override
            public void onResponse(Call<TipsDTO> call, Response<TipsDTO> response) {
                if(response.isSuccessful()){
                    listener.onSuccess(response.body());
                }
                else {
                    ErrorDTO errorDTO = new ErrorDTO(response.message(), response.code());
                    listener.onError(errorDTO);
                }
            }

            @Override
            public void onFailure(Call<TipsDTO> call, Throwable t) {

            }
        });
    }

}
