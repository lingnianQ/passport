package com.syt.passport.ex.handler;

import com.syt.passport.ex.ServiceException;
import com.syt.passport.web.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author sytsnb@gmail.com
 * @date 2022 2022/9/29 10:56
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public JsonResult<Void> handlerServiceException(ServiceException e) {
        log.info("handlerServiceException: {}", e.getMessage());
        return JsonResult.fail(e);
    }


    @ExceptionHandler
    public JsonResult<Void> handlerThrowable(Throwable e) {

        log.debug("处理Throwable");
        e.printStackTrace();//dev

        Integer serviceCode = 9999;
        String message = "系统未知错误,请联系管理员";
        return JsonResult.fail(serviceCode, message);

    }
}
