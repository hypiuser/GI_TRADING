package com.lsjgitrading.exception;
import cn.hutool.log.Log;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import cn.hutool.log.LogFactory;
import com.lsjgitrading.common.Result;
@ControllerAdvice(basePackages="com.lsjgitrading.controller")
public class GlobalExceptionHandler {
    private static final Log log = LogFactory.get();
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(HttpServletRequest request, Exception e){
        log.error("异常信息：",e);
        return Result.error();
    }
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public Result customError(HttpServletRequest request, CustomException e){
        return Result.error(e.getCode(), e.getMsg());
    }
}
