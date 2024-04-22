package com.example.usmobile.service.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.usmobile.DTO.DailyUsageDTO;
import com.example.usmobile.domain.Cycle;
import com.example.usmobile.repository.CycleRepository;
import com.example.usmobile.repository.DailyUsageRepository;
import com.example.usmobile.request.CycleRequest;
import com.example.usmobile.response.CurrentCycleDailyUsageResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CycleServiceImplTest {
    @Mock
    private CycleRepository cycleRepository;

    @Mock
    private DailyUsageRepository dailyUsageRepository;

    @InjectMocks
    private CycleServiceImpl cycleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCurrentCycleDailyUsage() {
        //Mocking data
        LocalDateTime currentDate = LocalDateTime.now();
        Cycle currentCycle = new Cycle("mdn", currentDate.minusDays(5), currentDate.plusDays(5), "userId");
        List<DailyUsageDTO> dailyUsageDTOList = new ArrayList<>();
        dailyUsageDTOList.add(new DailyUsageDTO(currentDate.minusDays(5), 100));

        //Mocking repository methods
        when(cycleRepository.findByUserIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                eq("userId"), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(List.of(currentCycle));
        when(dailyUsageRepository.findDailyUsageByUserIdAndMdnAndUsageDateBetween(
                eq("userId"), eq("mdn"), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(dailyUsageDTOList);

        //Calling the service method
        CycleRequest request = new CycleRequest("userId", "mdn");
        CurrentCycleDailyUsageResponse response = cycleService.getCurrentCycleDailyUsage(request);

        //Verifying the calls and asserting the response
        verify(cycleRepository, times(1)).findByUserIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                eq("userId"), any(LocalDateTime.class), any(LocalDateTime.class));
        verify(dailyUsageRepository, times(1)).findDailyUsageByUserIdAndMdnAndUsageDateBetween(
                eq("userId"), eq("mdn"),any(LocalDateTime.class), any(LocalDateTime.class));

        assertEquals(1, response.getDailyUsageDTOS().size());
        assertEquals(100, response.getDailyUsageDTOS().get(0).getDailyUsageInMb());
    }
}
