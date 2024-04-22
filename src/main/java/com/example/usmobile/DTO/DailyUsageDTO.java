package com.example.usmobile.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class DailyUsageDTO {
    private LocalDateTime usageDate;

    private double usedInMb;
}
