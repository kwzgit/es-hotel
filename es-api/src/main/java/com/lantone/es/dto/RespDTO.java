package com.lantone.es.dto;

import java.io.Serializable;

/**
 * @className: com.lantone.es.dto-> RespDTO
 * @description: 返回封装类
 * @author: kongwz
 * @createDate: 2021-09-22 13:16
 * @version: 1.0
 * @todo:
 */
public class RespDTO<T> implements Serializable {
    public String code = "0";
    public String msg = "";
    public T data;

    public RespDTO() {
    }

    public static RespDTO onSuc(Object data) {
        RespDTO resp = new RespDTO();
        resp.data = data;
        return resp;
    }

    public static RespDTO onSucBoth(String code, String msg, Object data) {
        RespDTO resp = new RespDTO();
        resp.code = code;
        resp.msg = msg;
        resp.data = data;
        return resp;
    }

    public static RespDTO onError(String errMsg) {
        RespDTO resp = new RespDTO();
        resp.code = "-1";
        resp.msg = errMsg;
        return resp;
    }

    @Override
    public String toString() {
        return "RespDTO{code='" + this.code + '\'' + ", msg='" + this.msg + '\'' + ", data=" + this.data + '}';
    }
}
