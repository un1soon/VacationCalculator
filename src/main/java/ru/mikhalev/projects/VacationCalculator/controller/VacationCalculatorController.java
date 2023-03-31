package ru.mikhalev.projects.VacationCalculator.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mikhalev.projects.VacationCalculator.dto.VacationInfo;
import ru.mikhalev.projects.VacationCalculator.dto.VacationPay;
import ru.mikhalev.projects.VacationCalculator.exception.IncorrectDataInRequest;
import ru.mikhalev.projects.VacationCalculator.service.VacationCalculatorService;

import java.io.IOException;
import java.util.List;

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
public class VacationCalculatorController {
    private final VacationCalculatorService vacationCalculatorService;
    /** Расчет отпускных на основе введенных сотрудником данных об отпуске */
    @GetMapping()
    public VacationPay calculateVacationPay(@RequestBody @Valid VacationInfo vacationInfo, BindingResult bindingResult) throws IOException {
        validateRequest(bindingResult);
        log.info(vacationCalculatorService.getAllDates(vacationInfo.getVacationStartDate(),
                vacationInfo.getVacationEndDate().plusDays(1)).toString());

        return vacationCalculatorService.calculatePay(vacationInfo);
    }

    /** Проверка введенных полей на наличие ошибок */
    private void validateRequest(BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            for(FieldError error : fieldErrors) {
                log.error(error.getField() + " - " + error.getDefaultMessage() + ";");
            }
            throw new IncorrectDataInRequest();
        }
    }
}
