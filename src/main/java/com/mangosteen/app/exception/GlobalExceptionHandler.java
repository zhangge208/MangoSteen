package com.mangosteen.app.exception;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // DRY: DON'T REPEAT YOURSELF
    @ExceptionHandler(ServiceException.class)
    ResponseEntity<ErrorResponse> handleServiceException(ServiceException ex) {
        return ResponseEntity.status(ex.getStatusCode())
                             .contentType(MediaType.APPLICATION_JSON)
                             .body(ErrorResponse.builder()
                                                .statusCode(ex.getStatusCode())
                                                .errorCode(ex.getErrorCode())
                                                .message(ex.getMessage())
                                                .errorType(ex.getErrorType())
                                                .build());
    }

    /*
    @ExceptionHandler(InvalidParameterException.class)
    ResponseEntity<ErrorResponse> handleInvalidParameterException(InvalidParameterException ex) {
        return ResponseEntity.badRequest()
                             .body(ErrorResponse.builder()
                                                .statusCode(HttpStatus.BAD_REQUEST.value())
                                                .errorCode(BizErrorCode.INVALID_PARAMETER)
                                                .message("User Id must be greater than 0")
                                                .errorType(ServiceException.ErrorType.Client)
                                                .build());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(ErrorResponse.builder()
                                                .statusCode(HttpStatus.NOT_FOUND.value())
                                                .errorCode(BizErrorCode.NOT_FOUND)
                                                .message(ex.getMessage())
                                                .errorType(ServiceException.ErrorType.Client)
                                                .build());
    }
    */


}
