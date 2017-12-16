package com.prorg.helper.result;

import java.util.ArrayList;
import java.util.List;

public class ValidationResponse {
    private final List<String> errors;

    private ValidationResponse(List<String> errors) {
        this.errors = errors;
    }

    public static ValidationResponse Success() {
        return new ValidationResponse(new ArrayList<>());
    }

    public static ValidationResponse Failure(List<String> errors) {
        return new ValidationResponse(errors);
    }

    public boolean isValid() {
        return errors.isEmpty();
    }

    public List<String> errors() {
        return errors;
    }
}
