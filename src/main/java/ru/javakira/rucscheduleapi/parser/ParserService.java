package ru.javakira.rucscheduleapi.parser;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javakira.rucscheduleapi.parser.record.Branch;
import ru.javakira.rucscheduleapi.parser.record.Employee;
import ru.javakira.rucscheduleapi.parser.record.Group;
import ru.javakira.rucscheduleapi.parser.record.Kit;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class ParserService {
    private final ExecutorService executor
            = Executors.newFixedThreadPool(50);

    private final ScheduleParser parser;

    public CompletableFuture<List<Branch>> branches() {
        return CompletableFuture.supplyAsync(parser::parseBranches, executor);
    }

    public CompletableFuture<List<Kit>> kits(String branch) {
        return CompletableFuture.supplyAsync(() -> parser.parseKits(branch), executor);
    }

    public CompletableFuture<List<Group>> groups(String branch, String kit) {
        return CompletableFuture.supplyAsync(() -> parser.parseGroups(branch, kit), executor);
    }

    public CompletableFuture<List<Employee>> employee(String branch) {
        return CompletableFuture.supplyAsync(() -> parser.parseEmployees(branch), executor);
    }

    public CompletableFuture<Cards> groupCards(@NonNull String branch, @NonNull String kit, @NonNull String group, @NonNull LocalDate searchDate) {
        return CompletableFuture.supplyAsync(() -> parser.parseGroupCards(branch, kit, group, searchDate), executor);
    }

    public CompletableFuture<Cards> employeeCards(@NonNull String branch, @NonNull String employee, @NonNull LocalDate searchDate) {
        return CompletableFuture.supplyAsync(() -> parser.parseEmployeeCards(branch, employee, searchDate), executor);
    }
}
