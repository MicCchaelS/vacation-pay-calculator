package ru.neoflex.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.neoflex.dto.VacationSalaryDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class VacationSalaryServiceImpl implements VacationSalaryService {

    /** Среднемесячное число календарных дней */
    private static final double DAYS_NUM_AVG_MONTHLY = 29.3;

    /** Код страны для API с государственными праздниками */
    private static final String COUNTRY_CODE = "RU";

    @Override
    public VacationSalaryDTO getVacationSalary(double averageSalaryPerYear, int vacationDaysNumber) {
        return new VacationSalaryDTO(calculateVacationSalary(averageSalaryPerYear, vacationDaysNumber));
    }

    @Override
    public VacationSalaryDTO getVacationSalary(double averageSalaryPerYear, int vacationDaysNumber,
                                               LocalDate vacationStartDate) {
        int year = vacationStartDate.getYear();

        Period period = Period.ofDays(1);

        // Массив дат каждого дня отпуска
        LocalDate[] vacationDaysDate = new LocalDate[vacationDaysNumber];
        vacationDaysDate[0] = vacationStartDate;
        for (int i = 1; i < vacationDaysNumber; i++) {
            vacationDaysDate[i] = vacationDaysDate[i - 1].plus(period);
        }

        // Список дат каждого праздничного дня в конкретном году
        List<LocalDate> publicHolidays = getPublicHolidaysFromAPI(year);

        /* Обновлённое количество отпускных дней в зависимости от года, в котором отпуск у сотрудника
         * и от государственных праздничных дней в этом году */
        vacationDaysNumber = getVacationDaysNumberWithoutPublicHolidays(vacationDaysDate,
                publicHolidays, vacationDaysNumber);

        return new VacationSalaryDTO(calculateVacationSalary(averageSalaryPerYear, vacationDaysNumber));
    }

    private double calculateVacationSalary(double averageSalaryPerYear, int vacationDaysNumber) {
        if (averageSalaryPerYear <= 0) {
            throw new IllegalArgumentException("Средняя зарплата за 12 месяцев должна быть положительным числом");
        }

        double vacationSalary = averageSalaryPerYear / DAYS_NUM_AVG_MONTHLY * vacationDaysNumber;
        BigDecimal result = new BigDecimal(vacationSalary).setScale(2, RoundingMode.HALF_UP);

        return result.doubleValue();
    }

    private List<LocalDate> getPublicHolidaysFromAPI(int year) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://date.nager.at/api/v3/PublicHolidays/" + year + "/" + COUNTRY_CODE;
        String response = restTemplate.getForObject(url, String.class);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonNode;
        try {
            jsonNode = mapper.readTree(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        List<LocalDate> publicHolidays = new ArrayList<>();
        for (int i = 0; i < jsonNode.size(); i++) {
            publicHolidays.add(LocalDate.parse(jsonNode.get(i).get("date").asText()));
        }

        return publicHolidays;
    }

    private int getVacationDaysNumberWithoutPublicHolidays(LocalDate[] vacationDaysDate,
                                                           List<LocalDate> publicHolidays,
                                                           int vacationDaysNumber
    ) {
        for (LocalDate vacationDayDate : vacationDaysDate) {
            if (publicHolidays.contains(vacationDayDate))
                vacationDaysNumber--;
        }

        return vacationDaysNumber;
    }
}