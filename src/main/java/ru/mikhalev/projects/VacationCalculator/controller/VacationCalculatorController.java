package ru.mikhalev.projects.VacationCalculator.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.mikhalev.projects.VacationCalculator.dto.VacationInfo;
import ru.mikhalev.projects.VacationCalculator.dto.VacationPay;
import ru.mikhalev.projects.VacationCalculator.service.VacationCalculatorService;
import ru.mikhalev.projects.VacationCalculator.util.annotation.DateYearCheck;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Ivan Mikhalev
 *
 * Расчет отпускных сотрудников
 *
 */

@RestController
@RequestMapping("/calculator")
@Slf4j
@RequiredArgsConstructor
@Validated
public class VacationCalculatorController {
    private final VacationCalculatorService vacationCalculatorService;

    /**
     * Расчет отпускных на основе введенных данных об отпуске
     */
    @GetMapping()
    public VacationPay calculateVacationPay(
            @Valid
            @RequestParam(value = "averageSalary")
            @Min(value = 10000, message = "Средняя заработная плата не может быть меньше чем 10000")
            @Max(value = 500000, message = "Средняя заработная плата не может быть больше чем 500000")
            @Schema(description = "Средняя зарплата сотрудника за год", implementation = BigDecimal.class)
            Double averageSalary,
            @NotNull
            @RequestParam(value = "startDate")
            @DateYearCheck
            LocalDate startDate,
            @RequestParam(value = "endDate")
            @NotNull
            @DateYearCheck
            LocalDate endDate) {
        VacationInfo vacationInfo = VacationInfo.builder()
                .averageSalary(averageSalary)
                .vacationStartDate(startDate)
                .vacationEndDate(endDate)
                .build();

        return vacationCalculatorService.calculatePay(vacationInfo);
    }
}
