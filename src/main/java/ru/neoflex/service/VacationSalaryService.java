package ru.neoflex.service;

import ru.neoflex.dto.VacationSalaryDTO;

import java.time.LocalDate;

public interface VacationSalaryService {
    VacationSalaryDTO getVacationSalary(double averageSalaryPerYear, int vacationDaysNumber);
    VacationSalaryDTO getVacationSalary(double averageSalaryPerYear, int vacationDaysNumber,
                                        LocalDate vacationStartDate);
}