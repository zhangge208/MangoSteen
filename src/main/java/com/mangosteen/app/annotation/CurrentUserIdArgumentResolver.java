package com.mangosteen.app.annotation;

import com.mangosteen.app.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class CurrentUserIdArgumentResolver implements HandlerMethodArgumentResolver {

    public static final String AUTHORIZATION = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    private final JWTUtil jwtUtil;

    @Autowired
    public CurrentUserIdArgumentResolver(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(CurrentUserId.class) != null
            && parameter.getParameterType().equals(Long.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String authHeader = webRequest.getHeader(AUTHORIZATION);
        String token = null;
        if (authHeader != null && authHeader.startsWith(TOKEN_PREFIX)) {
            token = authHeader.substring(TOKEN_PREFIX.length());
            return jwtUtil.extractUserId(token);
        }
        return null;
    }
}
