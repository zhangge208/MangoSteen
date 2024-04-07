package com.mangosteen.app.config;

import java.io.IOException;
import java.io.OutputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mangosteen.app.exception.BizErrorCode;
import com.mangosteen.app.exception.ErrorResponse;
import com.mangosteen.app.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class CustomizedAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        val result = ErrorResponse.builder()
                                  .statusCode(HttpStatus.FORBIDDEN.value())
                                  .errorCode(BizErrorCode.NO_AUTH)
                                  .message("Authentication exception happened")
                                  .errorType(ServiceException.ErrorType.Client)
                                  .build();


        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(String.valueOf(MediaType.APPLICATION_JSON));
        OutputStream outputStream = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(outputStream, result);
        outputStream.flush();


    }
}
