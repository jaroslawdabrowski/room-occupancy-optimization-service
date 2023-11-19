package com.jarek.roomoccupancyopt.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record RoomOccupancyCalculation(@Valid @NotNull RoomTypeUsage premium, @Valid @NotNull RoomTypeUsage economy) {
}
