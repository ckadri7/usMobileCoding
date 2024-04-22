package com.example.usmobile.controller;

import com.example.usmobile.domain.Cycle;
import com.example.usmobile.request.CycleRequest;
import com.example.usmobile.response.CurrentCycleDailyUsageResponse;
import com.example.usmobile.response.CycleHistoryResponse;
import com.example.usmobile.service.CycleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("api/v1/cycle")
public class CycleController {
    private final CycleService cycleService;


    @PostMapping("/current-cycle-daily-usage")
    public ResponseEntity<CurrentCycleDailyUsageResponse> getCurrentCycleDailyUsage(
                                        @RequestParam String userId,
                                        @RequestParam String mdn){
        CycleRequest request = new CycleRequest(userId,mdn);
        return ResponseEntity.status(HttpStatus.OK).body(cycleService.getCurrentCycleDailyUsage(request));
    }

    @PostMapping("/cycle-history")
    public ResponseEntity<CycleHistoryResponse> getCycleHistory(
                                                @RequestParam String userId,
                                                @RequestParam String mdn){
        CycleRequest request = new CycleRequest(userId,mdn);
        return ResponseEntity.status(HttpStatus.OK).body(cycleService.getCycleHistoryByMdn(request));
    }





}
