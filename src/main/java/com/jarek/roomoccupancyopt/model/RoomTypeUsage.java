package com.jarek.roomoccupancyopt.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record RoomTypeUsage(@NotNull @PositiveOrZero int occupied,
                            @NotNull @PositiveOrZero int totalIncome) {
}
