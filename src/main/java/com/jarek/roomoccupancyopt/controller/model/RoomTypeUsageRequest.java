package com.jarek.roomoccupancyopt.controller.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomTypeUsageRequest {
    @NotNull
    @PositiveOrZero
    private int free;
}
