package com.waes.scalableweb.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @autor Victor Wardi - @victorwardi on 2/9/2020
 *
 * * @see https://docs.jboss.org/hibernate/validator/5.0/reference/en-US/html/validator-customconstraints.html#validator-customconstraints-constraintannotation
 */

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = {EncodedOnBase64Validator.class})
@Documented
public @interface EncodedOnBase64 {

    String message() default "Must be encoded on base 64}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}