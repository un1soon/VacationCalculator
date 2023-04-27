package ru.mikhalev.projects.VacationCalculator.util.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.mikhalev.projects.VacationCalculator.util.annotation.DateYearCheck;

import java.time.LocalDate;

/**
 * @author Ivan Mikhalev
 *
 * Класс, проверяющий год, указанный в запросе
 *
 */

public class DateYearValidator implements ConstraintValidator<DateYearCheck, LocalDate> {
    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        return localDate.getYear() == 2023;
    }
}
