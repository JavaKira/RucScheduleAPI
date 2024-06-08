package ru.javakira.rucscheduleapi.statistic;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class StatisticFilter implements Filter {
    private final StatisticService statisticService;

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (request.getRequestURI().contains("/api")) {
            statisticService.apiRequest(
                    request.getMethod() + " " + request.getRequestURI()
            );
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
