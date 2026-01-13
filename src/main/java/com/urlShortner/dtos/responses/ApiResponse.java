package com.urlShortner.dtos.responses;


import lombok.Data;


@Data
public class ApiResponse {
    private boolean isSuccessful;
    private String message;
    private Object data;


    public ApiResponse(boolean isSuccessful, Object data) {
        this.isSuccessful = isSuccessful;
        this.data = data;
    }

    public ApiResponse(boolean isSuccessful, String message) {
        this.isSuccessful = isSuccessful;
        this.message = message;
    }
}
