package ru.mikhalev.projects.VacationCalculator.util.exception;

/**
 * @author Ivan Mikhalev
 * <p>
 * Исключение, выбрасываемое при ошибке в пути к файлу
 */

public class IncorrectPathToTheFile extends RuntimeException {
    @Override
    public String getMessage() {
        return "Server error. File along the given path not found";
    }
}
