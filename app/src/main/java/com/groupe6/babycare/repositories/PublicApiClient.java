package com.groupe6.babycare.repositories;

import android.content.Context;

import com.groupe6.babycare.BuildConfig;
import com.groupe6.babycare.interceptors.JWTInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PublicApiClient {

    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
