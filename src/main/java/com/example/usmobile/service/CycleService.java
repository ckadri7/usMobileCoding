package com.example.usmobile.service;

import com.example.usmobile.domain.Cycle;
import com.example.usmobile.request.CycleRequest;
import com.example.usmobile.response.CurrentCycleDailyUsageResponse;
import com.example.usmobile.response.CycleHistoryResponse;
import org.springframework.stereotype.Service;

@Service
public interface CycleService {
    CurrentCycleDailyUsageResponse getCurrentCycleDailyUsage(CycleRequest request);

    CycleHistoryResponse getCycleHistoryByMdn(CycleRequest request);

    Cycle createCycle(Cycle cycle);
}
