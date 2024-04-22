package com.example.usmobile.domain;

import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class DailyUsage {
    @Id
    String Id;
    String mdn;
    @Indexed
    String userId;
    LocalDateTime usageDate;

    @Nullable
    double usedInMb;

    public DailyUsage(String mdn, String userId, LocalDateTime usageDate, double usedInMb) {
        this.mdn = mdn;
        this.userId = userId;
        this.usageDate = usageDate;
        this.usedInMb = usedInMb;
    }
}
