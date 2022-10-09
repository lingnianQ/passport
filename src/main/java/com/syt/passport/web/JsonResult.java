package com.syt.passport.web;

import com.syt.passport.ex.ServiceException;
import io.swagger.annotations.ApiModelProperty;
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

    /**
     * 响应状态
     */
    @ApiModelProperty("业务状态码")
    private Integer state;

    /**
     * 响应信息
     */
    @ApiModelProperty("业务状态信息")
    private String message;

    /**
     * 成功时响应的数据
     */
    @ApiModelProperty("业务响应数据")
    private T data;

    public static <Void> JsonResult<Void> ok() {
        return ok(null, null);
    }

    public static <Void> JsonResult<Void> ok(String message) {
        return ok(null, message);
    }

    public static <T> JsonResult<T> ok(T data) {
        return new JsonResult<>(ServiceCode.OK.getValue(), null, data);
    }

    public static <T> JsonResult<T> ok(T data, String message) {
        return new JsonResult<>(ServiceCode.OK.getValue(), message, data);
    }

    public static <Void> JsonResult<Void> fail(ServiceException e) {
        return fail(e.getServiceCode().getValue(), e.getMessage());
    }

    public static <Void> JsonResult<Void> fail(Integer state, String message) {
        return new JsonResult<>(state, message, null);
    }


}
