package com.mangosteen.app.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ServiceException {

    /**
     * Constructor for ResourceNotFoundException
     * @param message error message
     */
    public ResourceNotFoundException(String message) {
        super(message);
        this.setStatusCode(HttpStatus.NOT_FOUND.value());
        this.setErrorCode(BizErrorCode.NOT_FOUND);
        this.setErrorType(ErrorType.Client);
    }
}
