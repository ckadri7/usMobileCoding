package com.example.usmobile.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Cycle {
    @Id
    String Id;
    @Indexed
    String mdn;
    LocalDateTime startDate;
    LocalDateTime endDate;
    String userId;

    public Cycle(String mdn, LocalDateTime startDate, LocalDateTime endDate, String userId) {
        this.mdn = mdn;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
    }
}
