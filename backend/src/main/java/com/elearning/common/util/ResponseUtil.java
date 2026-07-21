package com.elearning.common.util;

import com.elearning.common.response.ApiResponse;

public class ResponseUtil {

    private ResponseUtil() {
    }

    public static <T> ApiResponse<T> success(
            String message,
            T data) {

        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> success(T data) {

        return success("Success", data);
    }

    public static <T> ApiResponse<T> failure(String message) {

        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .build();
    }
}