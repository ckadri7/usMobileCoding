package com.example.usmobile.service.serviceImpl;

import com.example.usmobile.DTO.CycleHistory;
import com.example.usmobile.DTO.DailyUsageDTO;
import com.example.usmobile.domain.Cycle;
import com.example.usmobile.repository.CycleRepository;
import com.example.usmobile.repository.DailyUsageRepository;
import com.example.usmobile.request.CycleRequest;
import com.example.usmobile.response.CurrentCycleDailyUsageResponse;
import com.example.usmobile.response.CycleHistoryResponse;
import com.example.usmobile.service.CycleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CycleServiceImpl implements CycleService {
    private final CycleRepository cycleRepository;
    private final DailyUsageRepository dailyUsageRepository;

    @Override
    public CurrentCycleDailyUsageResponse getCurrentCycleDailyUsage(CycleRequest request) {
        //get the current cycle
        LocalDateTime currentDate = LocalDateTime.now();
        Cycle currentCycle = cycleRepository.findByUserIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(request.getUserId(), currentDate,currentDate).get(0);
        List<DailyUsageDTO> dailyUsageDTOList = dailyUsageRepository.findDailyUsageByUserIdAndMdnAndUsageDateBetween(request.getUserId(),request.getMdn(),currentCycle.getStartDate(),currentCycle.getEndDate());
        CurrentCycleDailyUsageResponse response = new CurrentCycleDailyUsageResponse();
        dailyUsageDTOList.stream().forEach(dto ->{response.getDailyUsageDTOS().add(dto);});
        return response;
    }

    @Override
    public CycleHistoryResponse getCycleHistoryByMdn(CycleRequest request) {
        List<CycleHistory> cycleHistoryDTOList = cycleRepository.findCycleHistoryByUserIdAndMdn(request.getUserId(), request.getMdn());
        CycleHistoryResponse response = new CycleHistoryResponse();
        cycleHistoryDTOList.stream().forEach(dto ->{
            response.getCycleHistoryDTOList().add(dto);
        });
        return response;
    }

    @Override
    public Cycle createCycle(Cycle cycle) {

        cycle.setStartDate(LocalDateTime.now());
        cycle.setEndDate(LocalDateTime.now().plusDays(29));
        return cycleRepository.insert(cycle);
    }


}
