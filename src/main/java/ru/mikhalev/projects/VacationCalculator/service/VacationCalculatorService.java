package ru.mikhalev.projects.VacationCalculator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.mikhalev.projects.VacationCalculator.dto.VacationInfo;
import ru.mikhalev.projects.VacationCalculator.dto.VacationPay;
import ru.mikhalev.projects.VacationCalculator.mapper.VacationPayMapper;
import ru.mikhalev.projects.VacationCalculator.util.exception.IncorrectDataInRequest;
import ru.mikhalev.projects.VacationCalculator.util.exception.IncorrectPathToTheFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * @author Ivan Mikhalev
 *
 * Сервис по расчету суммы отпускных
 *
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class VacationCalculatorService {
    /**
     * Маппер
     */
    private final VacationPayMapper vacationPayMapper;

    /** Путь до файла, содержащего даты праздников в 2023 году */
    @Value("${filepath.holidays.date}")
    private String filepathToHolidaysDate;

    /** Среднее количество дней в месяце */
    @Value("${average.amount.days}")
    private double averageAmountDays;

    /** Массив, содержаший даты праздников в 2023 году */
    private List<LocalDate> holidaysDates = new ArrayList<>();

    /** Метод, который рассчитывает сумму отпускных сотрудника */
    public VacationPay calculatePay(VacationInfo vacationInfo) {
        if(holidaysDates.isEmpty()) {
            try {
                holidaysDates = getHolidaysDates(filepathToHolidaysDate);
            } catch (IOException ex) {
                log.error("List с датами праздников пуст ");
                throw new IncorrectPathToTheFile();
            }
        }
        List<LocalDate> vacationDays = getAllVacationDates(vacationInfo.getVacationStartDate(), vacationInfo.getVacationEndDate());
        Integer amountHolidays = getAmountHolidayDaysInList(vacationDays, holidaysDates);
        return vacationPayMapper.toVacationPay(vacationInfo.getAverageSalary(), averageAmountDays, amountHolidays, vacationDays.size());
    }

    /** Получение количества праздничных дней в датах отпуска сотрудника */
    public Integer getAmountHolidayDaysInList(List<LocalDate> vacationDates, List<LocalDate> holidaysDates) {
        int counterHolidaysInList = 0;
        for (LocalDate vacationDate : vacationDates) {
            for (LocalDate holidayDate : holidaysDates) {
                if (vacationDate.equals(holidayDate)) {
                    counterHolidaysInList++;
                }
            }
        }

        int totalVacationAmount = vacationDates.size() - counterHolidaysInList;
        log.info("Количество праздничных дней в днях отпуска: " + counterHolidaysInList);
        log.info("Количество дней отпуска с учетом праздников: " + totalVacationAmount);
        return counterHolidaysInList;
    }

    /**
     * Получение списка дат отпуска сотрудника
     */
    public List<LocalDate> getAllVacationDates(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> allDates = startDate.
                datesUntil(endDate.plusDays(1)).
                toList();

        if (allDates.isEmpty()) {
            log.error("Дата начала отпуска позже, чем дата окончания");
            throw new IncorrectDataInRequest();
        }


        log.debug("Даты отпуска сотрудника: " + startDate.datesUntil(endDate.plusDays(1))
                .toList());
        return allDates;
    }

    /** Заполнение списка дат праздников при помощи дат из файла */
    public List<LocalDate> getHolidaysDates(String filePath) throws IOException {
        List<LocalDate> holidaysDates = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filePath));
        while(scanner.hasNextLine()) {
            holidaysDates.add(LocalDate.parse(scanner.nextLine()));
        }
        log.info("Даты праздников успешно добавлены в ArrayList");

        return holidaysDates;
    }
}
