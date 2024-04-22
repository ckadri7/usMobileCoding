package com.example.usmobile.repository;

import com.example.usmobile.DTO.DailyUsageDTO;
import com.example.usmobile.domain.DailyUsage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface DailyUsageRepository extends MongoRepository<DailyUsage,String> {
    @Query(value = "{ 'userId': ?0, 'mdn': ?1, 'usageDate': { $gte: ?2, $lte: ?3 } }", fields = "{ 'usageDate': 1, 'usedInMb': 1 }")
    List<DailyUsageDTO> findDailyUsageByUserIdAndMdnAndUsageDateBetween(String userId, String mdn, LocalDateTime startDate, LocalDateTime endDate);
}
