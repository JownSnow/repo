package com.changgou.goods.handler;


import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice     //声明   该类 是一个   异常处理类
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)    //声明  捕获 什么类型的异常
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();   //获取抛出异常的信息
        System.out.println(e.getMessage());
        return new Result(false, StatusCode.ERROR, "您的网络连接出现异常");
    }
}
