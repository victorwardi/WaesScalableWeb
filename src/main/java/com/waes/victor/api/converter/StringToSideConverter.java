package com.waes.victor.api.converter;

import com.waes.victor.api.enuns.Side;
import org.springframework.core.convert.converter.Converter;

/**
 * @author Victor Wardi - @victorwardi on 2/9/2020
 * @see "https://www.baeldung.com/spring-mvc-custom-data-binder"
 */
public class StringToSideConverter implements Converter<String, Side> {

    @Override
    public Side convert(String from) {
            return Side.valueOf(from.toUpperCase());
    }
}

