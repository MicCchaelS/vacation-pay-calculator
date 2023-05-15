package ru.neoflex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.dto.VacationSalaryDTO;
import ru.neoflex.service.VacationSalaryService;

import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@Validated
public class VacationSalaryController {
    private final VacationSalaryService vacationSalaryService;

    @Autowired
    public VacationSalaryController(VacationSalaryService vacationSalaryService) {
        this.vacationSalaryService = vacationSalaryService;
    }

    @GetMapping("/calculacte")
    public VacationSalaryDTO getVacationSalary(
            @RequestParam("averageSalaryPerYear")
            double averageSalaryPerYear,

            @RequestParam("vacationDays")
            @Min(value = 0, message = "Количество дней отпуска должно быть положительным целым числом")
            int vacationDays,

            @RequestParam("vacationStartDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            Optional<LocalDate> vacationStartDate
    ) {
        if (vacationStartDate.isPresent()) {
            return vacationSalaryService.getVacationSalary(averageSalaryPerYear, vacationDays, vacationStartDate.get());
        } else {
            return vacationSalaryService.getVacationSalary(averageSalaryPerYear, vacationDays);
        }
    }
}