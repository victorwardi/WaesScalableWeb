package com.waes.scalableweb.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.codec.binary.Base64;


/**
 * @autor Victor Wardi - @victorwardi on 2/9/2020
 */
public class EncodedOnBase64Validator implements ConstraintValidator<EncodedOnBase64, String> {

    @Override
    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
        if (object == null || object.isBlank()) {
            return false;
        } else {
            return Base64.isBase64(object);
        }
    }
}
