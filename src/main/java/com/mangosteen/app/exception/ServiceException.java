package com.mangosteen.app.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ServiceException extends RuntimeException {
    private int statusCode;
    private BizErrorCode errorCode;

    private ErrorType errorType;

    public enum ErrorType {
        Client,
        Service,
        Unknown
    }

    //private String requestId;

    public ServiceException(String message) {
        super(message);
    }
}
