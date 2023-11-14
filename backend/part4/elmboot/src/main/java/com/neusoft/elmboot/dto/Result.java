package com.neusoft.elmboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
    private int code;
    private Object result;
    private String msg;


    public static Result success(Object result) {
        return new Result(10000, result, "success");
    }

    public static Result failure(int code,String msg){
        return new Result(code, null, msg);
    }
}
