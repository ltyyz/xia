package com.github.loutai.xia.common;

import java.util.HashMap;

public class ServiceResult extends HashMap<String, Object> {

    private String code;
    private String msg;
    private Object data;

    void build() {
        super.put("code", code);
        super.put("msg", msg);
        super.put("data", data);
    }

    public static ServiceResult of(String code, String msg, Object data) {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.code = code;
        serviceResult.msg = msg;
        serviceResult.data = data;
        serviceResult.build();
        return serviceResult;
    }

    public static ServiceResult ofSuccess(String msg, Object data) {
        return of(Constant.RTN_CODE_SUCCESS, msg, data);
    }

    public static ServiceResult ofSuccess(Object data) {
        return ofSuccess("success", data);
    }

    public static ServiceResult ofSuccess() {
        return ofSuccess(null);
    }

    public static ServiceResult ofError(String msg, Object data) {
        return of(Constant.RNT_CODE_ERROR, msg, data);
    }

    public static ServiceResult ofError(String msg) {
        return ofError(msg, null);
    }

    public static ServiceResult ofError() {
        return ofError("failed");
    }

}
