package com.pengoo.brainTracker.dto;

import java.time.LocalDate;

public record WeekSummaryResponse(
        LocalDate date, int totalMinutesWeek, int totalXpWeek
) {}
