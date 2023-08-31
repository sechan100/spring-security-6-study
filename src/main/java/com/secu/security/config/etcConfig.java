package com.secu.security.config;


import com.secu.security.provider.CustomAuthenticationProvicer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class etcConfig {

//    @Bean
//    public AuthenticationProvider authenticationProvider(){
//        return new CustomAuthenticationProvicer();
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
