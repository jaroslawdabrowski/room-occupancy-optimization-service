package com.jarek.roomoccupancyopt.model;

import jakarta.validation.constraints.NotNull;

public record RoomOccupancyCalculation(@NotNull RoomTypeUsage premium, @NotNull RoomTypeUsage economy) {
}
