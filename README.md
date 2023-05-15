# Приложение "Калькулятор отпускных"

---

### Обзор

Приложение "Калькулятор отпускных".

Микросервис на SpringBoot + Java 11 c одним API:
GET "/calculacte"

Минимальные требования: Приложение принимает твою среднюю зарплату за 12 месяцев и количество дней отпуска - отвечает 
суммой отпускных, которые придут сотруднику.

Доп. задание: При запросе также можно указать точные дни ухода в отпуск, тогда должен проводиться рассчет отпускных с 
учётом праздников и выходных.

*Для получения списка праздников в определённому году мною был использован 
[Public Holiday API](https://date.nager.at/Api) 

---

### Использованные технологии:

* Java 11
* Spring Boot
* Maven
* Lombok

---

### Результаты работы программы:

GET "/calculacte"

#### 1) Запрос с указанием средней зарплаты сотрудника за 12 месяцев и количества дней отпуска

Запрос:  
http://localhost:8080/calculacte?averageSalaryPerYear=30000&vacationDays=14

Ответ:
```
{  
    "vacationSalary": 14334.47  
}
```

![Request-response1](https://github.com/MikKkhailS/test-task/blob/main/src/main/resources/images/Response%201.png?raw=true)

#### 2) Запрос с указанием средней зарплаты сотрудника за 12 месяцев, количества дней отпуска и дня даты начала отпуска

Запрос:  
http://localhost:8080/calculacte?averageSalaryPerYear=30000&vacationDays=14&vacationStartDate=2022-01-01

Ответ:
```
{
    "vacationSalary": 7167.24  
}
```

![Request-response2](https://github.com/MikKkhailS/test-task/blob/main/src/main/resources/images/Response%202.png?raw=true)
