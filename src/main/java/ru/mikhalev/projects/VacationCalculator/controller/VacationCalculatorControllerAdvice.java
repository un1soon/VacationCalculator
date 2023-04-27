package ru.mikhalev.projects.VacationCalculator.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.mikhalev.projects.VacationCalculator.util.exception.ErrorResponse;
import ru.mikhalev.projects.VacationCalculator.util.exception.IncorrectDataInRequest;
import ru.mikhalev.projects.VacationCalculator.util.exception.IncorrectPathToTheFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * @author Ivan Mikhalev
 *
 * Класс, обрабатывающий исключения
 *
 */
@ControllerAdvice
@Slf4j
public class VacationCalculatorControllerAdvice {
    @ExceptionHandler(IncorrectDataInRequest.class)
    public ResponseEntity<ErrorResponse> handleIncorrectDataInRequestException() {
        log.error("Неверные данные в запросе");
        ErrorResponse errorResponse = new ErrorResponse(
                "Incorrect data in request",
                LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectPathToTheFile.class)
    public ResponseEntity<ErrorResponse> handleIncorrectPathToTheFileException() {
        log.error("Неверный путь к файлу с датами праздников ");
        ErrorResponse errorResponse = new ErrorResponse(
                "Server error",
                LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ErrorResponse> handleDateTimeParseException(DateTimeParseException ex) {
        log.error("Ошибка преобразования строки в DateTime. Сообщение: " + ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                "Invalid date in request",
                LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
