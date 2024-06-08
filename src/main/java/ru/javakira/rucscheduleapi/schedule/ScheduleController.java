package ru.javakira.rucscheduleapi.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javakira.rucscheduleapi.parser.record.Branch;
import ru.javakira.rucscheduleapi.parser.record.Employee;
import ru.javakira.rucscheduleapi.parser.record.Group;
import ru.javakira.rucscheduleapi.parser.record.Kit;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
class ScheduleController {
    private final ScheduleService scheduleService;

    @GetMapping("/branches")
    public List<Branch> branches() throws ExecutionException, InterruptedException {
        return scheduleService.branches().get();
    }

    @GetMapping("/kits")
    public List<Kit> kits(String branch) throws ExecutionException, InterruptedException {
        return scheduleService.kits(branch).get();
    }

    @GetMapping("/groups")
    public List<Group> groups(String branch, String kit) throws ExecutionException, InterruptedException {
        return scheduleService.groups(branch, kit).get();
    }

    @GetMapping("/employees")
    public List<Employee> employees(String branch) throws ExecutionException, InterruptedException {
        return scheduleService.employees(branch).get();
    }
}
