package com.example.usmobile.repository;

import com.example.usmobile.DTO.CycleHistory;
import com.example.usmobile.domain.Cycle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;


public interface CycleRepository extends MongoRepository<Cycle,String> {

    List<Cycle> findByUserIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(String userId,LocalDateTime currentDate, LocalDateTime CurrentDate);

    @Query(value = "{ 'userId': ?0, 'mdn': ?1 }", fields = "{ 'cycleId': 1, 'startDate': 1, 'endDate': 1 }")
    List<CycleHistory> findCycleHistoryByUserIdAndMdn(String userId, String mdn);
}
