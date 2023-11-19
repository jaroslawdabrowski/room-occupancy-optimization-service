package com.jarek.roomoccupancyopt.controller.model;

import com.jarek.roomoccupancyopt.model.HotelAvailability;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomOccupancyOptimizationCalculationRequest {
    @Valid
    @NotNull
    private RoomTypeUsageRequest economy;

    @Valid
    @NotNull
    private RoomTypeUsageRequest premium;

    public HotelAvailability toHotelAvailability() {
        return new HotelAvailability(premium.getFree(), economy.getFree());
    }

}
