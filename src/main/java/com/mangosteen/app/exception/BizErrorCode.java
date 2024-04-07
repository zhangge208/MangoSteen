package com.mangosteen.app.exception;

public enum BizErrorCode {
    INVALID_PARAMETER("INVALID_PARAMETER"),
    NOT_FOUND("NOT_FOUND"),

    NO_AUTH("NO_AUTH"),

    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR");

    private String message;

    BizErrorCode(String message) {
        this.message = message;
    }
}
