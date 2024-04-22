package com.example.usmobile.response;

import com.example.usmobile.DTO.CycleHistory;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CycleHistoryResponse {
   List<CycleHistory> cycleHistoryDTOList = new ArrayList<>();
}
