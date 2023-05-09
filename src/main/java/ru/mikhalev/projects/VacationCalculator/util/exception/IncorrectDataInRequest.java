package ru.mikhalev.projects.VacationCalculator.util.exception;

/**
 * @author Ivan Mikhalev
 * <p>
 * Исключение, выбрасываемое при ошибках в запросе
 */

public class IncorrectDataInRequest extends RuntimeException {
    @Override
    public String getMessage() {
        return "Start date in request more than end date";
    }
}
