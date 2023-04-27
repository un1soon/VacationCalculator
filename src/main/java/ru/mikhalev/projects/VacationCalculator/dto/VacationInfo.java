package ru.mikhalev.projects.VacationCalculator.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mikhalev.projects.VacationCalculator.util.annotation.DateYearCheck;

import java.time.LocalDate;

/**
 * @author Ivan Mikhalev
 *
 * Класс, содержащий информацию об отпуске сотрудника
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacationInfo {

    /** Средняя зарплата сотрудника за год*/
    @Min(value = 10000, message = "Средняя заработная плата не может быть меньше чем 10000")
    @Max(value = 500000, message = "Средняя заработная плата не может быть больше чем 500000")
    private Double averageSalary;

    /** Дата начала отпуска */
    @NotNull
    @DateYearCheck
    private LocalDate vacationStartDate;
    /** Дата окончания отпуска */
    @NotNull
    @DateYearCheck
    private LocalDate vacationEndDate;
}
