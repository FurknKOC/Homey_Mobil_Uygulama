package com.furkan.homey.service;

import com.furkan.homey.exception.AuthenticationRequiredException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public abstract class BaseService {

    protected static final String SUCCESS_RESPONSE = "1";
    protected static final String FAIL_RESPONSE = "0";

    public String getLoginUserName() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(User.class::cast)
                .map(User::getUsername)
                .orElseThrow(AuthenticationRequiredException::new);
    }

}
