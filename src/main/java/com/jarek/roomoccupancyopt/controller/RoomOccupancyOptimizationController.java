package com.jarek.roomoccupancyopt.controller;

import com.jarek.roomoccupancyopt.controller.model.RoomOccupancyCalculationRepresentation;
import com.jarek.roomoccupancyopt.controller.model.RoomOccupancyOptimizationCalculationRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("room-occupancy-optimization")
@Tag(name = "Room Occupancy Optimization")
public class RoomOccupancyOptimizationController {
    private final RoomOccupancyOptimizationFacade roomOccupancyOptimizationFacade;

    public RoomOccupancyOptimizationController(RoomOccupancyOptimizationFacade roomOccupancyOptimizationFacade) {
        this.roomOccupancyOptimizationFacade = roomOccupancyOptimizationFacade;
    }

    @Operation(summary = "Creates calculation result that optimizes room occupancy at hotel", description = "Creates a new product as per the request body")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully calculated"),
            @ApiResponse(responseCode = "400", description = "Bad request - The calculation request is not valid"),
            @ApiResponse(responseCode = "500", description = "Internal server error - Something went wrong")
    })
    @PostMapping("calculation")
    public ResponseEntity<RoomOccupancyCalculationRepresentation> calculate(@RequestBody @Valid @NotNull RoomOccupancyOptimizationCalculationRequest request) {
        return ResponseEntity.ok(roomOccupancyOptimizationFacade.calculate(request));
    }

}
