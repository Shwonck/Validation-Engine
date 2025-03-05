package com.shwonck.validation_engine.utils;

public class CpfValidator {

    public static boolean isValid(String CPF) {
        // removes every character, except numbers
        CPF = CPF.replaceAll("[^0-9]", "");

        // CPFs formed by a sequence of equal numbers are considered invalid
        if (CPF == null || CPF.length() != 11 || CPF.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            // Calculate the first check digit
            int sum = 0;
            int weight = 10;
            for (int i = 0; i < 9; i++) {
                int num = Character.getNumericValue(CPF.charAt(i));
                sum += num * weight;
                weight--;
            }

            int remainder = 11 - (sum % 11);
            char dig10 = (remainder == 10 || remainder == 11) ? '0' : (char) (remainder + '0');

            // Calculate the second check digit
            sum = 0;
            weight = 11;
            for (int i = 0; i < 10; i++) {
                int num = Character.getNumericValue(CPF.charAt(i));
                sum += num * weight;
                weight--;
            }

            remainder = 11 - (sum % 11);
            char dig11 = (remainder == 10 || remainder == 11) ? '0' : (char) (remainder + '0');

            // Check if the calculated digits match the entered digits
            return (dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10));
        } catch (Exception e) {
            return false;
        }
    }
}