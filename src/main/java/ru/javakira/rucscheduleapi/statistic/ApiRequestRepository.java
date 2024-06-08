package ru.javakira.rucscheduleapi.statistic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiRequestRepository extends JpaRepository<ApiRequest, Long> {
}
