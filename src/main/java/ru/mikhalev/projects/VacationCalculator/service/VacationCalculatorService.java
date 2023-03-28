package ru.mikhalev.projects.VacationCalculator.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ru.mikhalev.projects.VacationCalculator.dto.VacationInfo;
import ru.mikhalev.projects.VacationCalculator.dto.VacationPay;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author Ivan Mikhalev
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class VacationCalculatorService {
    @Value("${average.amount.days}")
    private Double averageAmountDays;

    @Value("${filepath.holidays.date}")
    private String filepathToHolidaysDate;
    private List<LocalDate> holidaysDates = new ArrayList<>();

    public VacationPay calculatePay(VacationInfo vacationInfo) throws IOException {
        if(holidaysDates.isEmpty()) {
            holidaysDates = getHolidaysDates(filepathToHolidaysDate);
        }
        List<LocalDate> vacationDays = getAllDates(vacationInfo.getHolidayStartDate(), vacationInfo.getHolidayEndDate());
        Integer amountHolidays = getAmountHolidayDaysInList(vacationDays, holidaysDates);
        log.info(amountHolidays.toString());
        return new VacationPay((vacationInfo.getAverageSalary() / averageAmountDays) * (vacationDays.size() - amountHolidays));
    }

    public Integer getAmountHolidayDaysInList(List<LocalDate> vacationDates, List<LocalDate> holidaysDates) {
        Integer counterHolidaysInList = 0;
        for (LocalDate vacationDate : vacationDates) {
            for(LocalDate holidayDate : holidaysDates) {
                if(vacationDate.equals(holidayDate)) {
                    counterHolidaysInList++;
                }
            }
        }

        return counterHolidaysInList;
    }

    public List<LocalDate> getAllDates(LocalDate startDate, LocalDate endDate) {
        return startDate.datesUntil(endDate)
                .collect(Collectors.toList());
    }

    public List<LocalDate> getHolidaysDates(String filePath) throws IOException {
        List<LocalDate> holidaysDates = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filePath));
        String line = null;
        while(scanner.hasNextLine()) {
            line = scanner.nextLine();
            holidaysDates.add(LocalDate.parse(line));
        }
        log.info("Holiday dates have been added in list");

        return holidaysDates;
    }
}
