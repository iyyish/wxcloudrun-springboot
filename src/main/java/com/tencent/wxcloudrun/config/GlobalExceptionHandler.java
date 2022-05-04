package com.tencent.wxcloudrun.config;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description:
 * @Created: 2022/4/24
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ApiResponse handler(Exception e) {
        return ApiResponse.error(e.getMessage());
    }
}
