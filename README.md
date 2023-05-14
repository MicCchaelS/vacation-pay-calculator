# Приложение "Калькулятор отпускных"

---

### Обзор

Приложение "Калькулятор отпускных".

Микросервис на SpringBoot + Java 11 c одним API:
GET "/calculacte"

Минимальные требования: Приложение принимает твою среднюю зарплату за 12 месяцев и количество дней отпуска - отвечает 
суммой отпускных, которые придут сотруднику.

---

### Использованные технологии:

* Java 11
* Spring Boot
* Maven
* Lombok

---

### Запрос и ответ:

GET "/calculacte"

Запрос:

`http://localhost:8080/calculacte?averageSalaryPerYear=30000&vacationDays=14`

Ответ:

`{  
"vacationSalary": 14334.47  
}`

![Request-response](https://github.com/MikKkhailS/test-task/blob/main/src/main/resources/images/Response.png?raw=true)
