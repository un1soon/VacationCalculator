package ru.mikhalev.projects.VacationCalculator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.mikhalev.projects.VacationCalculator.dto.VacationInfo;
import ru.mikhalev.projects.VacationCalculator.dto.VacationPay;
import ru.mikhalev.projects.VacationCalculator.exception.IncorrectDataInRequest;
import ru.mikhalev.projects.VacationCalculator.mapper.VacationPayMapper;

import java.io.*;
import java.time.LocalDate;
import java.util.*;


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
    /** Маппер */
    private final VacationPayMapper vacationPayMapper = new VacationPayMapper();

    /** Путь до файла, содержащего даты праздников в 2023 году */
    @Value("${filepath.holidays.date}")
    private String filepathToHolidaysDate;

    /** Среднее количество дней в месяце */
    @Value("${average.amount.days}")
    private double averageAmountDays;

    /** Список, содержаший даты праздников в 2023 году */
    private List<LocalDate> holidaysDates = new ArrayList<>();

    /** Метод, расчитывающий сумму отпускных сотрудника */
    public VacationPay calculatePay(VacationInfo vacationInfo) throws IOException {
        if(holidaysDates.isEmpty()) {
            holidaysDates = getHolidaysDates(filepathToHolidaysDate);
        }
        List<LocalDate> vacationDays = getAllDates(vacationInfo.getVacationStartDate(), vacationInfo.getVacationEndDate());
        Integer amountHolidays = getAmountHolidayDaysInList(vacationDays, holidaysDates);
        return vacationPayMapper.toVacationPay(vacationInfo.getAverageSalary(), averageAmountDays, amountHolidays, vacationDays.size());
    }

    /** Получение количества праздничных дней в датах отпуска сотрудника */
    public Integer getAmountHolidayDaysInList(List<LocalDate> vacationDates, List<LocalDate> holidaysDates) {
        Integer counterHolidaysInList = 0;
        for (LocalDate vacationDate : vacationDates) {
            for(LocalDate holidayDate : holidaysDates) {
                if(vacationDate.equals(holidayDate)) {
                    counterHolidaysInList++;
                }
            }
        }
        log.info("Number of holidays on employee vacation dates: " + counterHolidaysInList);
        return counterHolidaysInList;
    }

    /** Получение списка дат отпуска сотрудника */
    public List<LocalDate> getAllDates(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> allDates = startDate.
                datesUntil(endDate.plusDays(1)).
                toList();

        if(allDates.isEmpty())
            throw new IncorrectDataInRequest();

        log.info("Vacation dates of employee: " + startDate.datesUntil(endDate.plusDays(1))
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
        log.info("Holiday dates have been added in list");

        return holidaysDates;
    }
}
