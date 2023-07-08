package com.kc.cloud.labs.aws.exceptions;


public class LabServerlessException extends Exception {

    private static final long serialVersionUID = 1L;
    public static final String GENERAL_ERROR_HTTP_CODE = "500";
    final String httpReturnCode;

    public LabServerlessException(String httpReturnCode, String message, Throwable cause) {
        super(message, cause);
        this.httpReturnCode = httpReturnCode;
    }

    public LabServerlessException(String message, Throwable cause) {
        this(GENERAL_ERROR_HTTP_CODE, message, cause);
    }
    public LabServerlessException(String httpReturnCode, String message) {
        super(message);
        this.httpReturnCode = httpReturnCode;
    }

    public LabServerlessException(String message) {
        this(GENERAL_ERROR_HTTP_CODE, message);
    }

}
