package ru.mikhalev.projects.VacationCalculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Ivan Mikhalev
 * <p>
 * Класс, содержащий тесты для VacationCalculatorController
 */

@SpringBootTest
@AutoConfigureMockMvc
public class VacationCalculatorControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Проверка на правильность ответа при запросе с корректными данными")
    void shouldReturnValidVacationPay() throws Exception {
        var requestBuilder = MockMvcRequestBuilders.get("/calculator?averageSalary=100000&startDate=2023-01-01&endDate=2023-01-14");

        this.mockMvc.perform(requestBuilder)
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json("""
                                {
                                    "amount" : 20478.0
                                }
                                """
                        )
                );
    }

    @Test
    @DisplayName("Проверка на правильность ответа при запросе с некорректными данными")
    void errorResponseWithStatusBadRequest() throws Exception {
        var requestBuilder = MockMvcRequestBuilders.get("/calculator?averageSalary=1000&startDate=2023-01-01&endDate=2023-01-14");

        this.mockMvc.perform(requestBuilder)
                .andExpectAll(
                        status().isBadRequest(),
                        content().contentType(MediaType.APPLICATION_JSON)
                );
    }
}
