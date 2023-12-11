package com.groupe6.babycare.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.auth0.android.jwt.JWT;

public final class TokenManager {

    private static final String KEY_ACCESS_TOKEN = "accessToken";

    private SharedPreferencesUtils sharedPreferencesUtils;

    private Context context;

    public TokenManager(Context context) {
        this.context = context;
        this.sharedPreferencesUtils = SharedPreferencesUtils.getInstance(context);
    }

    public void saveToken(String token) {
        sharedPreferencesUtils.store(KEY_ACCESS_TOKEN, token);
    }

    public String getToken() {
        return sharedPreferencesUtils.getValue(KEY_ACCESS_TOKEN);
    }

    public void clearToken() {
        sharedPreferencesUtils.clairValue(KEY_ACCESS_TOKEN);
    }



    public void storeCredentials(String token){
        if(token != null) {
            JWT jwt = new JWT(token);
            Long id = jwt.getClaim("id").asLong();
            sharedPreferencesUtils.store("parentId",String.valueOf(id) );
        }
    }

}
