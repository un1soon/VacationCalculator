package ru.mikhalev.projects.VacationCalculator.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.mikhalev.projects.VacationCalculator.exception.ErrorResponse;

import java.time.LocalDateTime;

/**
 * @author Ivan Mikhalev
 *
 * Класс, обрабатывающий исключения
 *
 */
@ControllerAdvice
@Slf4j
public class VacationCalculatorControllerAdvice {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        log.error("Incorrect data in request");
        ErrorResponse errorResponse = new ErrorResponse(
                "Incorrect data in request",
                LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
