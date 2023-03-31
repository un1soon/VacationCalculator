package ru.mikhalev.projects.VacationCalculator.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Ivan Mikhalev
 *
 * Объект, отправляемый при ошибках в введенных данных об отпуске
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
