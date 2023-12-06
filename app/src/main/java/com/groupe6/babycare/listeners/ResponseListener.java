package com.groupe6.babycare.listeners;

import com.groupe6.babycare.dtos.error.ErrorDTO;

public interface ResponseListener<T> {

    void onSuccess(T response);

    void onError(ErrorDTO error);

}
