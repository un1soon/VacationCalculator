package ru.mikhalev.projects.VacationCalculator.util.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Ivan Mikhalev
 *
 * Объект, отправляемый при ошибках в программе
 *
 */
@Data
@AllArgsConstructor
public class ErrorResponse {
    /** Сообщение ошибки */
    private String message;

    /** Дата и время возникновения ошибки */
    private LocalDateTime timestamp;
}
