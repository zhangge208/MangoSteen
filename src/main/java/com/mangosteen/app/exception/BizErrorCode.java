package com.mangosteen.app.exception;

public enum BizErrorCode {
    INVALID_PARAMETER("INVALID_PARAMETER"),
    NOT_FOUND("NOT_FOUND");

    private String message;

    BizErrorCode(String message) {
        this.message = message;
    }
}
