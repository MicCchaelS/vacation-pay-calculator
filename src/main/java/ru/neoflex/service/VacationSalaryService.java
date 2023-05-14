package ru.neoflex.service;

import ru.neoflex.dto.VacationSalaryDTO;

public interface VacationSalaryService {
    VacationSalaryDTO getVacationSalary(double averageSalaryPerYear, int vacationDaysNumber);
}