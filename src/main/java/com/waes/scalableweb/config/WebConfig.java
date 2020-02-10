package com.waes.scalableweb.config;

import com.waes.scalableweb.converter.StringToSideConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Defines callback methods to customize the Java-based configuration for Spring MVC.
 *
 * @autor Victor Wardi - @victorwardi on 2/9/2020
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Set custom formatters
     *
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToSideConverter());
    }
}