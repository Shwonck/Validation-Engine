package com.shwonck.validation_engine.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AgeValidator {

    public static boolean isValid(String dateOfBirth) {
        int ageNumber;
        try {
                // defines the format of the input data
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

                // convert String to LocalDate
                LocalDate birthDate = LocalDate.parse(dateOfBirth, formatter);

                // get the current date
                LocalDate currentDate = LocalDate.now();

                // calculate age
                int age = Period.between(birthDate, currentDate).getYears();

            // arbitrarily chose 110 as a maximum age to avoid erroneously large entries
            // chose 18 years as a minimum age
            return (age >= 18 && age <= 110);
        } catch (DateTimeParseException error) {
            return (false);
        }
    }
}