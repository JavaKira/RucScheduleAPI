package ru.javakira.rucscheduleapi.statistic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StatisticService {
    private final ApiRequestRepository apiRequestRepository;
    private final ApiErrorResponseRepository apiErrorResponseRepository;

    public void apiRequest(String requestPath) {
        ApiRequest apiRequest = ApiRequest
                .builder()
                .requestPath(requestPath)
                .localDate(LocalDateTime.now())
                .build();
        apiRequestRepository.save(apiRequest);
    }

    public void errorResponse(String requestPath, int status, String message, String body, String queryString) {
        ApiErrorResponse apiErrorResponse = ApiErrorResponse
                .builder()
                .requestPath(requestPath)
                .status(status)
                .message(message)
                .localDate(LocalDateTime.now())
                .queryString(queryString)
                .requestBody(body)
                .build();
        apiErrorResponseRepository.save(apiErrorResponse);
    }
}
