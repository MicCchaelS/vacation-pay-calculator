package ru.neoflex.service;

import org.springframework.stereotype.Service;
import ru.neoflex.dto.VacationSalaryDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class VacationSalaryServiceImpl implements VacationSalaryService {

    /** Среднемесячное число календарных дней */
    private static final double DAYS_NUM_AVG_MONTHLY = 29.3;

    @Override
    public VacationSalaryDTO getVacationSalary(double averageSalaryPerYear, int vacationDaysNumber) {
        double vacationSalary = averageSalaryPerYear / DAYS_NUM_AVG_MONTHLY * vacationDaysNumber;
        BigDecimal result = new BigDecimal(vacationSalary).setScale(2, RoundingMode.HALF_UP);

        return new VacationSalaryDTO(result.doubleValue());
    }
}