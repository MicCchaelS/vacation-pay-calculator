package ru.neoflex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.dto.VacationSalaryDTO;
import ru.neoflex.service.VacationSalaryService;

@RestController
public class VacationSalaryController {
    private final VacationSalaryService vacationSalaryService;

    @Autowired
    public VacationSalaryController(VacationSalaryService vacationSalaryService) {
        this.vacationSalaryService = vacationSalaryService;
    }

    @GetMapping("/calculacte")
    public VacationSalaryDTO getVacationSalary(
            @RequestParam("averageSalaryPerYear") double averageSalaryPerYear,
            @RequestParam("vacationDays") int vacationDays) {
        return vacationSalaryService.getVacationSalary(averageSalaryPerYear, vacationDays);
    }
}