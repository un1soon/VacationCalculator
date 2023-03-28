package ru.mikhalev.projects.VacationCalculator.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author Ivan Mikhalev
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacationInfo {
    private Double averageSalary;
    private LocalDate holidayStartDate;
    private LocalDate holidayEndDate;
}
