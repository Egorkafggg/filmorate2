package ru.yandex.practicum.filmorate.util.annotation;


import jakarta.validation.Constraint;

import jakarta.validation.Payload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = DateValidation.class)
public @interface DateValidation {
    String messege()default "DateTime is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
