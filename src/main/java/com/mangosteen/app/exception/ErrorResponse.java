package com.mangosteen.app.exception;

import lombok.Builder;
import lombok.Data;

/**
 * Standard Error Response.
 */
@Data
@Builder
public class ErrorResponse {
    private int statusCode;
    private String message;
    private ServiceException.ErrorType errorType;
    private BizErrorCode errorCode;
}
