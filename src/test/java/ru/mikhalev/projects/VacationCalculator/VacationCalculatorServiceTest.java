package ru.mikhalev.projects.VacationCalculator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mikhalev.projects.VacationCalculator.dto.VacationInfo;
import ru.mikhalev.projects.VacationCalculator.mapper.VacationPayMapper;
import ru.mikhalev.projects.VacationCalculator.service.VacationCalculatorService;
import ru.mikhalev.projects.VacationCalculator.util.exception.IncorrectDataInRequest;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * @author Ivan Mikhalev
 * <p>
 * Класс, содержащий тесты для VacationCalculatorService
 */

@SpringBootTest
public class VacationCalculatorServiceTest {

    /**
     * Корректные отпускные данные
     */
    private final VacationInfo correctVacationInfo = VacationInfo
            .builder()
            .averageSalary(100000)
            .vacationStartDate(LocalDate.parse("2023-01-01"))
            .vacationEndDate(LocalDate.parse("2023-01-14"))
            .build();
    /**
     * Некорректные отпускные данные
     */
    private final VacationInfo incorrectVacationInfo = VacationInfo
            .builder()
            .averageSalary(100000)
            .vacationStartDate(LocalDate.parse("2023-01-15"))
            .vacationEndDate(LocalDate.parse("2023-01-14"))
            .build();
    @Autowired
    private VacationCalculatorService vacationCalculatorService = new VacationCalculatorService(new VacationPayMapper());

    @Test
    @DisplayName("При верном пути до файла метод должен добавлять праздники в List")
    public void methodShouldReturnListWithHolidayDates() throws IOException {
        List<LocalDate> holidayDates = vacationCalculatorService.getHolidaysDates("holidaysDate");

        Assert.assertNotNull(holidayDates);
        Assert.assertEquals(14, holidayDates.size());
    }

    @Test
    @DisplayName("При неверном пути к файлу метод должен выбрасывать IOException")
    public void methodShouldThrowIOException() {
        Throwable thrown = assertThrows(IOException.class, () -> {
            vacationCalculatorService.getHolidaysDates("holidaysDat");
        });

        Assert.assertNotNull(thrown.getMessage());
    }

    @Test
    @DisplayName("При верно указанных в запросе датах метод должен возвращать List, содержащий даты праздников сотрудника")
    public void methodShouldReturnListWithEmployeeVacationDays() {
        List<LocalDate> vacationDays = vacationCalculatorService.getAllVacationDates(correctVacationInfo.getVacationStartDate(),
                correctVacationInfo.getVacationEndDate());

        Assert.assertNotNull(vacationDays);
        Assert.assertEquals(14, vacationDays.size());
    }

    @Test
    @DisplayName("При неверно указанных датах отпуска метод должен выбрасывать IncorrectDataInRequest ")
    public void methodShouldThrowIncorrectDataInRequestException() throws IncorrectDataInRequest {
        Throwable thrown = assertThrows(IncorrectDataInRequest.class, () -> {
            vacationCalculatorService.getAllVacationDates(incorrectVacationInfo.getVacationStartDate(),
                    incorrectVacationInfo.getVacationEndDate());
        });

        Assert.assertNotNull(thrown.getMessage());
    }
}
