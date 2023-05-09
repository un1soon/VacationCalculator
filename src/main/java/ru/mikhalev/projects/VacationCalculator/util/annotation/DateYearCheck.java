package ru.mikhalev.projects.VacationCalculator.util.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.mikhalev.projects.VacationCalculator.util.validator.DateYearValidator;

import java.lang.annotation.*;

/**
 * @author Ivan Mikhalev
 *
 * Аннотация для проверки года, указанного в запросе
 *
 */

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateYearValidator.class)
@Documented
public @interface DateYearCheck {
    String message() default "Отпускные рассчитываются только для 2023 года";
    Class<?> [] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
