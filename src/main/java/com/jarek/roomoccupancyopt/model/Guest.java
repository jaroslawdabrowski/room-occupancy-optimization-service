package com.jarek.roomoccupancyopt.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record Guest(@NotNull String id, @NotNull @Positive int budget) {
}
