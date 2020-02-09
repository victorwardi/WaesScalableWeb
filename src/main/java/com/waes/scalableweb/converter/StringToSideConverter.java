package com.waes.scalableweb.converter;

import com.waes.scalableweb.enuns.Side;
import org.springframework.core.convert.converter.Converter;

/**
 * @autor Victor Wardi - @victorwardi on 2/9/2020
 * @see "https://www.baeldung.com/spring-mvc-custom-data-binder"
 */
public class StringToSideConverter implements Converter<String, Side> {

    @Override
    public Side convert(String from) {
            return Side.valueOf(from.toUpperCase());
    }
}

