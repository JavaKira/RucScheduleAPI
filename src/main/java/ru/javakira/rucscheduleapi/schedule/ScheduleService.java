package ru.javakira.rucscheduleapi.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import ru.javakira.rucscheduleapi.parser.Cards;
import ru.javakira.rucscheduleapi.parser.ParserService;
import ru.javakira.rucscheduleapi.parser.record.*;
import ru.javakira.rucscheduleapi.schedule.employee.EmployeeRequest;
import ru.javakira.rucscheduleapi.schedule.student.StudentRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    public final ParserService parserService;

    public CompletableFuture<List<Branch>> branches() {
        return parserService.branches();
    }

    public CompletableFuture<List<Kit>> kits(String branch) {
        return parserService.kits(branch);
    }

    public CompletableFuture<List<Group>> groups(String branch, String kit) {
        return parserService.groups(branch, kit);
    }

    public CompletableFuture<List<Employee>> employees(String branch) {
        return parserService.employee(branch);
    }

    public CompletableFuture<Optional<Card>> studentDay(LocalDate date, StudentRequest request) {
        return parserService
                .groupCards(request.getBranch(), request.getKit(), request.getGroup(), date)
                .thenApply(cards -> cards
                        .getList()
                        .stream()
                        .filter(card -> card.date().equals(date))
                        .findAny());
    }

    public CompletableFuture<Optional<Cards>> studentWeek(LocalDate date, StudentRequest request) {
        CompletableFuture<Cards> cardsCompletableFuture = parserService
                .groupCards(request.getBranch(), request.getKit(), request.getGroup(), date);

        return cardsCompletableFuture.thenApply(cards -> cards.getList().isEmpty() ? Optional.empty() : Optional.of(cards));
    }

    public CompletableFuture<Optional<Card>> employeeDay(LocalDate date, EmployeeRequest request) {
        return parserService
                .employeeCards(request.getBranch(), request.getEmployee(), date)
                .thenApply(cards -> cards
                        .getList()
                        .stream()
                        .filter(card -> card.date().equals(date))
                        .findAny());
    }

    public CompletableFuture<Optional<Cards>> employeeWeek(LocalDate date, EmployeeRequest request) {
        CompletableFuture<Cards> cardsCompletableFuture = parserService
                .employeeCards(request.getBranch(), request.getEmployee(), date);

        return cardsCompletableFuture.thenApply(cards -> cards.getList().isEmpty() ? Optional.empty() : Optional.of(cards));
    }
}
