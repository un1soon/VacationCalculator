package ru.mikhalev.projects.VacationCalculator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mikhalev.projects.VacationCalculator.dto.VacationInfo;
import ru.mikhalev.projects.VacationCalculator.dto.VacationPay;
import ru.mikhalev.projects.VacationCalculator.service.VacationCalculatorService;

import java.io.IOException;

/**
 * @author Ivan Mikhalev
 */

@RestController
@RequestMapping("/calculator")
@Slf4j
@RequiredArgsConstructor
public class VacationCalculatorController {
    private final VacationCalculatorService vacationCalculatorService;

    @GetMapping()
    public VacationPay calculateVacationPay(@RequestBody VacationInfo vacationInfo) throws IOException {
        log.info(String.valueOf(vacationInfo));
        log.info(vacationCalculatorService.getAllDates(vacationInfo.getHolidayStartDate(), vacationInfo.getHolidayEndDate().plusDays(1)).toString());
        return vacationCalculatorService.calculatePay(vacationInfo);
    }
}
