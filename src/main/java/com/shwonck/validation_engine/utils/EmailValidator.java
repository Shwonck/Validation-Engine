package com.shwonck.validation_engine.utils;

import java.util.regex.Pattern;

public class EmailValidator {

    public static boolean isValid(String email) {

        // regular expression to match valid email formats
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        // compile the regex
        Pattern p = Pattern.compile(emailRegex);

        // check if email matches the pattern
        return email != null && p.matcher(email).matches();
    }
}
