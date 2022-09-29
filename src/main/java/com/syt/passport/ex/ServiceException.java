package com.syt.passport.ex;

import com.syt.passport.web.ServiceCode;

/**
 * 异常
 *
 * @author sytsnb@gmail.com
 * @date 2022 2022/9/29 10:52
 */
public class ServiceException extends RuntimeException {

    private ServiceCode serviceCode;

    public ServiceException(ServiceCode serviceCode, String message) {
        super(message);
        this.serviceCode = serviceCode;
    }

    public ServiceCode getServiceCode() {
        return serviceCode;
    }
}
