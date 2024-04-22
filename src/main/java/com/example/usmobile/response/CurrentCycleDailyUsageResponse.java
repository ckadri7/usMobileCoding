package com.example.usmobile.response;

import com.example.usmobile.DTO.DailyUsageDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CurrentCycleDailyUsageResponse {
    List<DailyUsageDTO> dailyUsageDTOS = new ArrayList<>() ;

}
