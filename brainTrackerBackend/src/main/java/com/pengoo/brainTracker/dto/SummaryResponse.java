package com.pengoo.brainTracker.dto;

import java.time.LocalDate;

public record SummaryResponse(
        LocalDate date, int minutesStudied, int xpEarned
){}
