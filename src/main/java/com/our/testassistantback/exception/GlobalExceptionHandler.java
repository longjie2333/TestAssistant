package com.our.testassistantback.exception;


import com.our.testassistantback.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result ex(Exception ex){
        String errType = ex.getClass().toString();

        if (errType.equals("com.our.testassistantback.pojo.Result")) {
            return Result.error("Token 过期了");
        }

        return Result.error(ex.getMessage());
    }
}
