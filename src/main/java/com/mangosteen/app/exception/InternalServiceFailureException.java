package com.mangosteen.app.exception;

import org.springframework.http.HttpStatus;

public class InternalServiceFailureException extends ServiceException {
    public InternalServiceFailureException(String message) {
        super(message);
        this.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        this.setErrorCode(BizErrorCode.INTERNAL_SERVER_ERROR);
        this.setErrorType(ErrorType.Service);
    }
}
