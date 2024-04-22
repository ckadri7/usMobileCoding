package com.example.usmobile.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CycleHistory {
    private String id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
