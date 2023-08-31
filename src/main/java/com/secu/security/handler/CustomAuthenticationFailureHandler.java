package com.secu.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.attribute.UserPrincipalNotFoundException;


@Component
@Slf4j
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMsg = "Invaild Login Access";

        if(exception instanceof BadCredentialsException) {
            errorMsg = "Invaild Password";

        } else if(exception instanceof UsernameNotFoundException){
            errorMsg = "Username Not Found";
        }

        setDefaultFailureUrl("/login?error=true&exception=" + errorMsg);

        super.onAuthenticationFailure(request, response, exception);
    }
}
