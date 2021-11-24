package com.clases.security.usuarios.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class DbAuthManager implements AuthenticationProvider {


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        System.out.println(username);
        System.out.println(authentication.getCredentials());

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
