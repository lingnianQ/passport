package com.syt.passport.web;

import com.syt.passport.ex.ServiceException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 响应返回值
 *
 * @author sytsnb@gmail.com
 * @date 2022 2022/9/29 10:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResult<T> implements Serializable {

    private Integer state;

    private String message;

    private T data;

    public static <Void> JsonResult<Void> ok() {
        return ok(null, null);
    }

    public static <Void> JsonResult<Void> ok(String message) {
        return ok(message, null);
    }

    public static <T> JsonResult<T> ok(T data) {
        return new JsonResult<>(ServiceCode.OK.getValue(), null, data);
    }

    public static <T> JsonResult<T> ok(String message, T data) {
        return new JsonResult<>(ServiceCode.OK.getValue(), message, data);
    }

    public static <Void> JsonResult<Void> fail(ServiceException e) {
        return fail(e.getServiceCode().getValue(), e.getMessage());
    }

    public static <Void> JsonResult<Void> fail(Integer state, String message) {
        return new JsonResult<>(state, message, null);
    }


}
