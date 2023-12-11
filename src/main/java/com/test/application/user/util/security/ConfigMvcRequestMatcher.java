package com.test.application.user.util.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

/**
 * Clase MvcRequestMatcher.Builder
 *
 * @author William Villaueva
 * @version 1.0.0
 */
@Component
public class ConfigMvcRequestMatcher {


    @Bean
    public MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }

}
