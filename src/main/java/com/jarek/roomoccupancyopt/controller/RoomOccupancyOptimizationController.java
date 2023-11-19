package com.jarek.roomoccupancyopt.controller;

import com.jarek.roomoccupancyopt.controller.model.RoomOccupancyCalculationRepresentation;
import com.jarek.roomoccupancyopt.controller.model.RoomOccupancyOptimizationCalculationRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("room-occupancy-optimization")
public class RoomOccupancyOptimizationController {
    private final RoomOccupancyOptimizationFacade roomOccupancyOptimizationFacade;

    public RoomOccupancyOptimizationController(RoomOccupancyOptimizationFacade roomOccupancyOptimizationFacade) {
        this.roomOccupancyOptimizationFacade = roomOccupancyOptimizationFacade;
    }

    @PostMapping("calculation")
    public ResponseEntity<RoomOccupancyCalculationRepresentation> calculate(@RequestBody @Valid @NotNull RoomOccupancyOptimizationCalculationRequest request) {
        return ResponseEntity.ok(roomOccupancyOptimizationFacade.calculate(request));
    }

}
