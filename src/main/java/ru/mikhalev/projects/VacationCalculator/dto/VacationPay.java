package ru.mikhalev.projects.VacationCalculator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ivan Mikhalev
 *
 * Класс, содержащий сумму отпускных, которую получит сотрудник
 *
 */

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class VacationPay {
    /** Сумма отпускных, которая будет начислена сотруднику */
    private Double amount;
}
