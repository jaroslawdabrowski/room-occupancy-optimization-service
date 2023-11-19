package com.jarek.roomoccupancyopt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jarek.roomoccupancyopt.controller.model.RoomOccupancyCalculationRepresentation;
import com.jarek.roomoccupancyopt.controller.model.RoomOccupancyOptimizationCalculationRequest;
import com.jarek.roomoccupancyopt.controller.model.RoomTypeUsageRepresentation;
import com.jarek.roomoccupancyopt.controller.model.RoomTypeUsageRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RoomOccupancyOptimizationController.class)
class RoomOccupancyOptimizationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomOccupancyOptimizationFacade roomOccupancyOptimizationFacade;


    @Test
    public void shouldReturnValidResultWhenValidRequest() throws Exception {
        // given
        RoomOccupancyOptimizationCalculationRequest request = RoomOccupancyOptimizationCalculationRequest
                .builder()
                .economy(RoomTypeUsageRequest.builder().free(3).build())
                .premium(RoomTypeUsageRequest.builder().free(4).build())
                .build();
        RoomOccupancyCalculationRepresentation response = RoomOccupancyCalculationRepresentation.builder()
                .premium(RoomTypeUsageRepresentation.builder().free(2).occupied(2).totalIncome(300).build())
                .economy(RoomTypeUsageRepresentation.builder().free(1).occupied(3).totalIncome(100).build())
                .build();
        when(roomOccupancyOptimizationFacade.calculate(any())).thenReturn(response);

        // when then
        mockMvc.perform(post("/room-occupancy-optimization/calculation")
                        .content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.economy.occupied").value(response.getEconomy().getOccupied()))
                .andExpect(jsonPath("$.economy.free").value(response.getEconomy().getFree()))
                .andExpect(jsonPath("$.economy.totalIncome").value(response.getEconomy().getTotalIncome()));
    }

    @Test
    public void shouldFailValidationWhenFreeIsNegative() throws Exception {
        // given
        RoomOccupancyOptimizationCalculationRequest request = RoomOccupancyOptimizationCalculationRequest
                .builder()
                .economy(RoomTypeUsageRequest.builder().free(3).build())
                .premium(RoomTypeUsageRequest.builder().free(-4).build())
                .build();

        // when then
        mockMvc.perform(post("/room-occupancy-optimization/calculation")
                        .content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldFailValidationWhenNoPremiumPart() throws Exception {
        // given
        RoomOccupancyOptimizationCalculationRequest request = RoomOccupancyOptimizationCalculationRequest
                .builder()
                .economy(RoomTypeUsageRequest.builder().free(3).build())
                .build();

        // when then
        mockMvc.perform(post("/room-occupancy-optimization/calculation")
                        .content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    public static String asJsonString(final Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }

}