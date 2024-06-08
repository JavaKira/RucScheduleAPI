package ru.javakira.rucscheduleapi.schedule.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.javakira.rucscheduleapi.parser.Cards;
import ru.javakira.rucscheduleapi.parser.record.Card;
import ru.javakira.rucscheduleapi.schedule.ScheduleService;

import java.time.LocalDate;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employee")
public class EmployeeScheduleController {
    private final ScheduleService scheduleService;

    @GetMapping("/day")
    public Card day(String date, EmployeeRequest request) throws ExecutionException, InterruptedException {
        LocalDate localDate = LocalDate.parse(date);

        return scheduleService
                .employeeDay(localDate, request)
                .get()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Расписания на день для " + date + " нет"
                ));
    }

    @GetMapping("/week")
    public Cards week(String date, EmployeeRequest request) throws ExecutionException, InterruptedException {
        LocalDate localDate = LocalDate.parse(date);

        return scheduleService
                .employeeWeek(localDate, request)
                .get()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Расписания на неделю для " + date + " нет"
                ));
    }
}
