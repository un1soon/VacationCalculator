package ru.mikhalev.projects.VacationCalculator.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacationInfo {

    /**
     * Средняя зарплата сотрудника за год
     */
    @Min(value = 10000, message = "Средняя заработная плата не может быть меньше чем 10000")
    @Max(value = 500000, message = "Средняя заработная плата не может быть больше чем 500000")
    @Schema(description = "Средняя зарплата сотрудника за год", example = "100000")
    private double averageSalary;

    /** Дата начала отпуска */
    @NotNull
    @DateYearCheck
    @Schema(description = "Дата начала отпуска", example = "2023-01-01")
    private LocalDate vacationStartDate;
    /** Дата окончания отпуска */
    @NotNull
    @DateYearCheck
    @Schema(description = "Дата окончания отпуска", example = "2023-01-14")
    private LocalDate vacationEndDate;
}
