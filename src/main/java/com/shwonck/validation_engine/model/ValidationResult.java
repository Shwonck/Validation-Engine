package com.shwonck.validation_engine.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ValidationResult {
    private boolean valid;
    private double confidenceScore;

    public void setErrorMessage(String string) {

    }
}
