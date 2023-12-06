package com.groupe6.babycare.dtos.error;

public class ErrorDTO {

    private String message;

    private int code;

    public ErrorDTO(String message, int code) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String toString() {
        return "ErrorDTO{" +
                "message='" + message + '\'' +
                ", code=" + code +
                '}';
    }
}
