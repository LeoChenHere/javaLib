package org.leochen.javaLib.result;

public enum ResultCode implements IResultCode {
    FINISHED(0, "Finished"),
    /**
     * 200系列成功响应
     */
    SUCCESS(200, "Success"),
    REQUEST_METHOD_NOT_SUPPORT(405, "Request Method not Support"),

    ERR_SERVER(500, "Server Error"),
    FAILED(999, "操作失败"),

    /**
     * 1000系列 入参数据错误相关响应
     */
    ERR_DATA(1000, "Data error"),



    ERR_PARAM(1100, "Request param error"),


    ERR_LOGIN(1200, "Login or register error"),

    UNAUTHORIZED(1201, "access denied"),

    FORBIDDEN(1202, "No access"),

    /**
     * 2000系列 数据库相关错误响应
     */
    ERR_DBFAIL(2000, "Database error"),
    /**
     * 3000系列 网络错误响应
     */
    ERR_NETWORK(3000, "Network error"),

    /**
     * 4000系列 其他错误响应
     */
    FAIL(4000, "Failed"),
    ;

    private int code;
    private String message;

    private ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
