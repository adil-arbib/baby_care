package com.groupe6.babycare.interceptors;

import android.content.Context;

import com.groupe6.babycare.utils.TokenManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class JWTInterceptor implements Interceptor {


    private Context context;

    private TokenManager tokenManager;

    public JWTInterceptor(Context context) {
        this.context = context;
        tokenManager = new TokenManager(context);
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder requestBuilder = original.newBuilder()
                .header("Authorization", "Bearer " + tokenManager.getToken())
                .method(original.method(), original.body());
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
