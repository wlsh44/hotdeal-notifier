package com.example.hotdealnotifier.common.exception;

public class HotDealNotifierException extends RuntimeException {

    private final ErrorCode errorCode;

    public HotDealNotifierException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public HotDealNotifierException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }
}
