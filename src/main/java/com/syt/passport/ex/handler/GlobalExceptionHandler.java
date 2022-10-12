package com.syt.passport.ex.handler;

import com.syt.passport.ex.ServiceException;
import com.syt.passport.web.JsonResult;
import com.syt.passport.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

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
    public JsonResult handleBindException(BindException e) {
        log.debug("捕获到BindException：{}", e.getMessage());
        // 以下2行代码，如果有多种错误时，将随机获取其中1种错误的信息，并响应
        // 当配置了“快速失败”后，Spring Validation检查永远最多只有1种错误
        String message = e.getFieldError().getDefaultMessage();
        return JsonResult.fail(ServiceCode.ERR_BAD_REQUEST.getValue(), message);
        // ===============================
        // 以上代码，如果有多种错误时，将获取所有错误信息，并响应
        // StringBuilder stringBuilder = new StringBuilder();
        // List<FieldError> fieldErrors = e.getFieldErrors();
        // for (FieldError fieldError : fieldErrors) {
        //     stringBuilder.append(fieldError.getDefaultMessage());
        //  }
        // return JsonResult.fail(ServiceCode.ERR_BAD_REQUEST, stringBuilder.toString());
    }

    @ExceptionHandler
    public JsonResult handleConstraintViolationException(ConstraintViolationException e) {
        log.debug("捕获到ConstraintViolationException：{}", e.getMessage());
        StringBuilder stringBuilder = new StringBuilder();
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            stringBuilder.append(constraintViolation.getMessage());
        }
        return JsonResult.fail(ServiceCode.ERR_BAD_REQUEST.getValue(), stringBuilder.toString());
    }

    @ExceptionHandler
    public JsonResult<Void> handlerHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {

        log.debug("处理HttpRequestMethodNotSupportedException");
//        e.printStackTrace();//dev
        Integer serviceCode = 7777;
        String message = "请求方式错误,请联系管理员";
        log.info(message);
        return JsonResult.fail(serviceCode, message);

    }

    public static final String MESSAGE_UNAUTHORIZED = "登录失败，用户名或密码错！";

    @ExceptionHandler(
            {
                    InternalAuthenticationServiceException.class,
                    BadCredentialsException.class
            }
    )
    public JsonResult<Void> handlerInternalAuthenticationServiceException(AuthenticationException e) {
        log.debug("处理{}: 异常信息: {}", e.getClass().getName(), e.getMessage());
        return JsonResult.fail(ServiceCode.ERR_UNAUTHORIZED.getValue(), MESSAGE_UNAUTHORIZED);

    }

//    @ExceptionHandler
//    public JsonResult<Void> handlerBadCredentialsException(BadCredentialsException e) {
//        log.debug("捕获BadCredentialsException: {}", e.getMessage());
//        return JsonResult.fail(ServiceCode.ERR_UNAUTHORIZED.getValue(), MESSAGE_UNAUTHORIZED);
//    }
//
    @ExceptionHandler
    public JsonResult<Void> handlerDisabledException(DisabledException e) {
        log.debug("捕获DisabledException: {}", e.getMessage());
        return JsonResult.fail(ServiceCode.ERR_UNAUTHORIZED_DISABLED.getValue(), e.getMessage());
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
