package com.syt.passport.web;

/**
 * 枚举状态码
 *
 * @author sytsnb@gmail.com
 * @date 2022 2022/9/29 10:51
 */
public enum ServiceCode {
    /**
     * 成功状态
     */
    OK(20000),
    ERR_BAD_REQUEST(40000),
    ERR_UNAUTHORIZED(40100),
    ERR_UNAUTHORIZED_DISABLED(40110),
    ERR_FORBIDDEN(40300),
    ERR_NOT_FOUND(40400),
    ERR_CONFLICT(40900),
    ERR_INSERT(50000),
    ERR_DELETE(50100),
    ERR_UPDATE(50200),
    ERR_JWT_EXPIRED(60000),
    ERR_JWT_PARSE(60100);


    private int value;

    ServiceCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
