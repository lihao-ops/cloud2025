package com.hli.vo;


import lombok.Data;

@Data
//通用响应结果包装类
public class ResultVO<T> {
    //状态码 200：OK,401：Unauthorized,403: Forbidden,404：Not Found
    private Integer code = 200;

    //返回请求信息
    protected String message = "OK";

    //返回结果对象
    private T data;
}