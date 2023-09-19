package com.raimis.blogas.util;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Constraint(validatedBy = PhoneNumberValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {
   String message() default "{javax.validation.constraint.NotNull.message}";

   Class<?>[] groups() default {};

   Class<? extends Payload>[] payload() default {};
   PhoneNumberType phoneNumberType() default PhoneNumberType.FULL;
}