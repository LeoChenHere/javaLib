package org.leochen.result;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class Result<T> implements Serializable {

    private int code;
    private String msg;
    private T data;

    public Result() {
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 配合静态对象直接设置 data
     *
     * @param data
     * @return
     */
    public Result setData(T data) {
        return new Result(this.code, this.msg, data);
    }

    //=================================================================//
    //=========================常用响应code  ==========================//
    //=================================================================//

    /**
     * 200系列成功响应
     */
    public static final Result SUCCESS = new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage());

    /**
     * 1000系列 入参数据错误相关响应
     */
    public static final Result ERR_DATA = new Result(ResultCode.ERR_DATA.getCode(), ResultCode.ERR_DATA.getMessage());
    public static final Result ERR_PARAM = new Result(ResultCode.ERR_PARAM.getCode(), ResultCode.ERR_PARAM.getMessage());
    public static final Result ERR_LOGIN = new Result(ResultCode.ERR_LOGIN.getCode(), ResultCode.ERR_LOGIN.getMessage());

    /**
     * 2000系列 数据库相关错误响应
     */
    public static final Result ERR_DBFAIL = new Result(ResultCode.ERR_DBFAIL.getCode(), ResultCode.ERR_DBFAIL.getMessage());

    /**
     * 3000系列 网络错误响应
     */
    public static final Result ERR_NETWORK = new Result(ResultCode.ERR_NETWORK.getCode(), ResultCode.ERR_NETWORK.getMessage());

    /**
     * 4000系列 其他错误响应
     */
    public static final Result FAIL = new Result(ResultCode.FAIL.getCode(), ResultCode.FAIL.getMessage());

    public static <T> Result<T> failed(String message) {
        return new Result<T>(ResultCode.FAIL.getCode(), message, null);
    }

    public static <T> Result<T> failed(int code, String message) {
        return new Result<T>(code, message, null);
    }

    public static <T> Result<T> failed(IResultCode errorCode, String message) {
        return new Result<T>(errorCode.getCode(), message, null);
    }

    public static <T> Result<T> failed(IResultCode errorCode) {
        return new Result<T>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    public static <T> Result<T> validateFailed(String message) {
        return new Result<T>(ResultCode.ERR_PARAM.getCode(), message, null);
    }

}
