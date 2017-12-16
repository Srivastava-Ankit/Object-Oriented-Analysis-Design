package com.prorg.helper.validator;

import com.prorg.helper.Password;
import com.prorg.helper.contraint.PasswordHashMatch;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Random;

public class PasswordHashMatchValidator implements ConstraintValidator<PasswordHashMatch, Object> {
    private String passwordString;
    private String passwordSalt;
    private String passwordHash;
    // TODO: Framework constraint. Is refactoring possible?
    private Password passwordHelper = new Password(new Random());

    @Override
    public void initialize(final PasswordHashMatch constraintAnnotation) {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        passwordString = constraintAnnotation.passwordString();
        passwordSalt = constraintAnnotation.passwordSalt();
        passwordHash = constraintAnnotation.passwordHash();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        try {
            String password = BeanUtils.getProperty(value, passwordString);
            String salt = BeanUtils.getProperty(value, passwordSalt);
            String hash = BeanUtils.getProperty(value, passwordHash);
            return passwordHelper.isExpectedPassword(password, salt, hash);
        } catch (Exception ignore) {
            // ignore
        }
        return true;
    }
}
