package ru.javakira.rucscheduleapi.schedule;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import ru.javakira.rucscheduleapi.statistic.StatisticService;

@ControllerAdvice
@RequiredArgsConstructor
public class ScheduleControllerAdvice {
    private final StatisticService statisticService;

    @ExceptionHandler(ResponseStatusException.class)
    public void handleException(ResponseStatusException e, HttpServletRequest request) {
        statisticService.errorResponse(
                request.getMethod() + " " + request.getRequestURI(),
                e.getStatusCode().value(),
                e.getMessage(),
                "",
                request.getQueryString()
        );
        throw e;
    }

    @ExceptionHandler(RuntimeException.class)
    public void handleException(RuntimeException e, HttpServletRequest request) {
        statisticService.errorResponse(
                request.getMethod() + " " + request.getRequestURI(),
                500,
                e.getMessage(),
                "",
                request.getQueryString()
        );
        throw e;
    }
}
