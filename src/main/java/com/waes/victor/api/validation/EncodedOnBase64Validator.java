package com.waes.victor.api.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * @author Victor Wardi - @victorwardi on 2/9/2020
 */
public class EncodedOnBase64Validator implements ConstraintValidator<EncodedOnBase64, String> {

    @Override
    public boolean isValid(String encodedData, ConstraintValidatorContext constraintContext) {

         // This annotation is not responsible for validate if string is null or blank.
        if (encodedData == null || encodedData.isBlank()) {
            return true;
        } else {
            // Check if string matches base64
            return encodedData.matches("^[A-Za-z0-9+/\\r\\n]+={0,2}$");
        }
    }
}
