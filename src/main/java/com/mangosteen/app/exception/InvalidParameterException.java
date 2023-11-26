package com.mangosteen.app.exception;

import org.springframework.http.HttpStatus;

@SuppressWarnings("checkstyle:SummaryJavadoc")
public class InvalidParameterException extends ServiceException {

    /**
     * Constructor for InvalidParameterException
     * @param message error message
    */
    public InvalidParameterException(String message) {
        super(message);
        this.setStatusCode(HttpStatus.BAD_REQUEST.value());
        this.setErrorCode(BizErrorCode.INVALID_PARAMETER);
        this.setErrorType(ErrorType.Client);
    }
}
