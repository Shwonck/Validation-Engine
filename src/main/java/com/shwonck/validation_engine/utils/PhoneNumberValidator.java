package com.shwonck.validation_engine.utils;

public class PhoneNumberValidator {

    public static boolean isValid(String phone){
        // removes every character, except numbers
        phone = phone.replaceAll("[^0-9]", "");

        // basic verification
        // check if the phone number has 10 or 11 digits, as it could be DDD(2) + phone(8) or DDD(2) + cellphone(9)
        return (phone.length() == 10 || phone.length() == 11);
    }
}
