package com.jarek.roomoccupancyopt.model;

import jakarta.validation.constraints.NotNull;

public record OccupancyCalculation(@NotNull RoomTypeUsage premium, @NotNull RoomTypeUsage economy) {
}
