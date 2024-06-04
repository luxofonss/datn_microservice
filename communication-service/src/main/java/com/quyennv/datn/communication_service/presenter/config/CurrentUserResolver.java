package com.quyennv.datn.communication_service.presenter.config;

import com.quyennv.datn.assignment_service.presenter.usecases.security.CurrentUser;
import com.quyennv.datn.assignment_service.presenter.usecases.security.UserPrincipal;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNullApi;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.List;
import java.util.UUID;

public class CurrentUserResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(CurrentUser.class) != null;
    }

    @Override
    public Object resolveArgument( MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        JwtAuthenticationToken authentication = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            UUID id = UUID.fromString(jwt.getClaimAsString("id"));
            String username = jwt.getClaimAsString("username");
            String email = jwt.getClaimAsString("email");
            String password = jwt.getClaimAsString("password");
            List<String> roles = jwt.getClaimAsStringList("roles");

            // Return the custom object as needed. For example:
            return new UserPrincipal(id, username, email, password, roles);
        }
        return null;
    }
}
