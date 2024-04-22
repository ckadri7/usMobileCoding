package com.example.usmobile.repository;

import com.example.usmobile.DTO.CycleHistory;
import com.example.usmobile.domain.Cycle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
public class CycleRepositoryTest {

    @Autowired
    private CycleRepository cycleRepository;

    @BeforeEach
    public void setUp() {
        // Initialize test data or perform setup if needed
    }

    @AfterEach
    public void tearDown() {
        // Clean up after each test if needed
    }

    @Test
    public void testFindByUserIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual() {
        LocalDateTime currentDate = LocalDateTime.now();
        List<Cycle> cycles = cycleRepository.findByUserIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual("userId", currentDate, currentDate);
        // Assert or perform validations on the returned cycles
        assertEquals(0, cycles.size()); // Example assertion
    }

    @Test
    public void testFindCycleHistoryByUserIdAndMdn() {
        List<CycleHistory> cycleHistoryList = cycleRepository.findCycleHistoryByUserIdAndMdn("userId", "mdn");
        // Assert or perform validations on the returned cycle history list
        assertEquals(0, cycleHistoryList.size()); // Example assertion
    }
}

