package ru.mikhalev.projects.VacationCalculator.mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.mikhalev.projects.VacationCalculator.dto.VacationPay;

/**
 * @author Ivan Mikhalev
 *
 * Маппер для объектов VacationPay
 *
 */

@Component
@RequiredArgsConstructor
@Slf4j
public class VacationPayMapper {

    /** Мэппинг в сумму отпусных
     * @param averageSalary средняя зарплата сотрудника
     * @param averageAmountDays среднее количество дней в месяце - константа равная 29,3
     * @param amountHolidays количество праздничных дней в датах отпуска
     * @param vacationDays количество дней отпуска
     * */
    public VacationPay toVacationPay(Double averageSalary, Double averageAmountDays, Integer amountHolidays, Integer vacationDays) {
        return VacationPay.builder().
                amount((double) Math.round((averageSalary / averageAmountDays) * (vacationDays - amountHolidays))).
                build();
    }
}
