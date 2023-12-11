package com.groupe6.babycare.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.auth0.android.jwt.JWT;

import java.time.Instant;

public final class TokenManager {

    private static final String KEY_ACCESS_TOKEN = "token";

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


    public void storeCredentials(String token) {
        if (token != null) {
            JWT jwt = new JWT(token);
            Long id = jwt.getClaim("id").asLong();
            Long expiredAt = jwt.getExpiresAt().getTime();
            sharedPreferencesUtils.store("parentId", String.valueOf(id));
            sharedPreferencesUtils.store("expiredAt", String.valueOf(expiredAt));

        }
    }

    public boolean isLoggedIn() {
        return sharedPreferencesUtils.getValue("token") != null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean tokenNotExpired() {
        if (!isLoggedIn()) return false;
        return Long.parseLong(sharedPreferencesUtils.getValue("expiredAt")) > Instant.now().toEpochMilli();

    }

}
