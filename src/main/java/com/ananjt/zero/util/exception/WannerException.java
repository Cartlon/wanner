package com.ananjt.zero.util.exception;

/**
 * Created by Admin on 2017/7/17.
 */
public class WannerException extends RuntimeException {

    private String errorCode;

    private boolean propertiesKey = true;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isPropertiesKey() {
        return propertiesKey;
    }

    public void setPropertiesKey(boolean propertiesKey) {
        this.propertiesKey = propertiesKey;
    }

    public WannerException(String message) {
        super(message);
    }

    public WannerException(String errorCode, String message) {
        this(errorCode, message, true);
    }


    public WannerException(String errorCode, String message, boolean propertiesKey) {
        super(message);
        this.setErrorCode(errorCode);
        this.setPropertiesKey(propertiesKey);
    }


    public WannerException(String errorCode, String message, Throwable cause, boolean propertiesKey) {
        super(message, cause);
        this.setErrorCode(errorCode);
        this.setPropertiesKey(propertiesKey);
    }


    public WannerException(String message, Throwable cause) {
        super(message, cause);
    }
}
