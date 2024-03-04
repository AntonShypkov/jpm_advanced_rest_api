package com.epam.advanced.java.config;

import com.epam.advanced.java.converter.SubscriptionRequestDtoToSubscriptionConverter;
import com.epam.advanced.java.converter.SubscriptionToSubscriptionResponseDtoConverter;
import com.epam.advanced.java.converter.UserRequestDtoToUserConverter;
import com.epam.advanced.java.converter.UserToUserResponseDtoConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new UserRequestDtoToUserConverter());
        registry.addConverter(new UserToUserResponseDtoConverter());

        registry.addConverter(new SubscriptionRequestDtoToSubscriptionConverter());
        registry.addConverter(new SubscriptionToSubscriptionResponseDtoConverter());
    }
}
