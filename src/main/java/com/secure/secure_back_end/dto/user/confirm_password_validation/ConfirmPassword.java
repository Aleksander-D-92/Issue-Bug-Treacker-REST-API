package com.secure.secure_back_end.dto.user.confirm_password_validation;

import com.secure.secure_back_end.dto.user.binding.UserRegistrationForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfirmPassword implements ConstraintValidator<ValidateConfirmPassword, UserRegistrationForm>
{

    @Override
    public void initialize(ValidateConfirmPassword constraintAnnotation)
    {

    }

    @Override
    public boolean isValid(UserRegistrationForm userRegistrationForm, ConstraintValidatorContext context)
    {
        return userRegistrationForm.getPassword().equals(userRegistrationForm.getConfirmPassword());
    }
}
