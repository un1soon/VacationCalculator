package ru.mikhalev.projects.VacationCalculator.controller;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.mikhalev.projects.VacationCalculator.util.exception.ErrorResponse;
import ru.mikhalev.projects.VacationCalculator.util.exception.IncorrectDataInRequest;
import ru.mikhalev.projects.VacationCalculator.util.exception.IncorrectPathToTheFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * @author Ivan Mikhalev
 * <p>
 * Класс, обрабатывающий исключения
 */
@ControllerAdvice
@Slf4j
public class VacationCalculatorControllerAdvice {
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> MissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error(e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectDataInRequest.class)
    public ResponseEntity<ErrorResponse> IncorrectDataInRequestException(IncorrectDataInRequest e) {
        log.error(e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> ConstraintViolationException(ConstraintViolationException e) {
        log.error(e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectPathToTheFile.class)
    public ResponseEntity<ErrorResponse> handleIncorrectPathToTheFileException(IncorrectPathToTheFile e) {
        log.error(e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ErrorResponse> handleDateTimeParseException(DateTimeParseException e) {
        log.error(e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
