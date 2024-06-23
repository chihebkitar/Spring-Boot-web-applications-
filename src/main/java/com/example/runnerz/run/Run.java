package com.example.runnerz.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.time.LocalDateTime;


public record Run(
        @Id
        Integer id,
         @NotEmpty
         String title,
         LocalDateTime startedOn,
         LocalDateTime completedOn,
         @Positive
         Integer miles,
         Location location,
        @Version
        Integer version
) {
    public Run {
        if (miles < 0) {
            throw new IllegalArgumentException("Miles must be greater than or equal to 0");
        }
        if (startedOn.isAfter(completedOn)) {
            throw new IllegalArgumentException("Completed date must be after started date");
        }
    }
}
