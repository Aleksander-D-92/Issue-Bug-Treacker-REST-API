package com.secure.secure_back_end.dto.user.confirm_password_validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ConfirmPassword.class})
public @interface ValidateConfirmPassword
{
    String message() default "password field does not match confirm password field";

    Class<?>[] groups() default {

    };

    Class<? extends Payload>[] payload() default {};
}
