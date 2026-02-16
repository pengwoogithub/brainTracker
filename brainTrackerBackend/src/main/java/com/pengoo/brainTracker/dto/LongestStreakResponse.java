package com.pengoo.brainTracker.dto;

import java.time.LocalDate;

public record LongestStreakResponse(
        int streak, LocalDate startDate, LocalDate endDate
) {}
