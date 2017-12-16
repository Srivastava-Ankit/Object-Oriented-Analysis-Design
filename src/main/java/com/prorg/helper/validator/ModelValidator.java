package com.prorg.helper.validator;

import com.prorg.helper.result.ValidationResponse;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ModelValidator {

    private Validator validator;

    @Autowired
    public ModelValidator(Validator validator) {
        this.validator = validator;
    }

    public <T> ValidationResponse validate(T model){
        Set<ConstraintViolation<T>> violations = validator.validate(model);
        List<String> errors = new ArrayList<>();
        for(ConstraintViolation<T> violation : violations) {
            errors.add(violation.getMessage());
        }
        return errors.isEmpty() ? ValidationResponse.Success() : ValidationResponse.Failure(errors);
    }
}
