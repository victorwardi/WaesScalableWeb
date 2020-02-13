package com.waes.victor.api.config;

import com.waes.victor.api.converter.StringToSideConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Defines callback methods to customize the Java-based configuration for Spring MVC.
 *
 * @author Victor Wardi - @victorwardi on 2/9/2020
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Set custom formatters from String to Side Enum
     *
     * @param registry Formatt
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToSideConverter());
    }
}