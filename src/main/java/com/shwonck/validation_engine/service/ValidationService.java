package com.shwonck.validation_engine.service;


import com.shwonck.validation_engine.model.UserData;
import com.shwonck.validation_engine.model.ValidationResult;
import com.shwonck.validation_engine.utils.AgeValidator;
import com.shwonck.validation_engine.utils.CpfValidator;
import com.shwonck.validation_engine.utils.EmailValidator;
import com.shwonck.validation_engine.utils.PhoneNumberValidator;
import org.springframework.stereotype.Service;

@Service
public class  ValidationService {

    public static ValidationResult validate(UserData userData) {
        ValidationResult result = new ValidationResult();
        boolean isValid = CpfValidator.isValid(userData.getCpf()) &&
                AgeValidator.isValid(userData.getBirthDate()) &&
                EmailValidator.isValid(userData.getEmail()) &&
                PhoneNumberValidator.isValid(userData.getPhone());

        result.setValid(isValid);
        result.setConfidenceScore(isValid ? Math.random() * 10 : 0);
        return result;
    }
}