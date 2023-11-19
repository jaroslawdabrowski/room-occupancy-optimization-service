package com.jarek.roomoccupancyopt.controller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Room Type Usage Request")
public class RoomTypeUsageRequest {
    @NotNull
    @PositiveOrZero
    @Schema(description = "Amount of free rooms", example = "17")
    private int free;
}
