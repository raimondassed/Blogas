package com.raimis.blogas.util;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

   private PhoneNumberType phoneNumberType;

   @Override
   public void initialize(PhoneNumber constraintAnnotation) {
       phoneNumberType = constraintAnnotation.phoneNumberType();
   }

   @Override
   public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
       return phoneNumberType.equals(PhoneNumberType.FULL) ? hasValidFullNumber(s) : hasValidPartNumber(s);
   }

   private boolean hasValidFullNumber(String phoneNumber) {
       if (phoneNumber.length() != 12 || phoneNumber.charAt(0) != '+' || !phoneNumber.substring(1, 5).equals("3706")) {
           return false;
       }
       return true;
   }

   private boolean hasValidPartNumber(String phoneNumber) {
       if (phoneNumber.length() != 9 || !phoneNumber.substring(0, 2).equals("86")) {
           return false;
       }
       return true;
   }
}
