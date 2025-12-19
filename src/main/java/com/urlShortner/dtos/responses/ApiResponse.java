package com.urlShortner.dtos.responses;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApiResponse {
    private boolean isSucessful;
    private Object data;
}
