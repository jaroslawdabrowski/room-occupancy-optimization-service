package com.jarek.roomoccupancyopt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jarek.roomoccupancyopt.controller.model.RoomOccupancyOptimizationCalculationRequest;
import com.jarek.roomoccupancyopt.controller.model.RoomTypeUsageRequest;
import com.jarek.roomoccupancyopt.load.TestDataLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
public class RoomOccupancyOptimizationServiceApplicationTests {

	@Autowired
	private TestDataLoader testDataLoader;

	@Autowired
	private MockMvc mockMvc;

	@BeforeAll
	public void loadTestGuests() throws Exception {
		testDataLoader.load();
	}

	@Test
	void shouldReturn0CalculationWhenNoRooms() throws Exception {
		// given
		RoomOccupancyOptimizationCalculationRequest request = RoomOccupancyOptimizationCalculationRequest
				.builder()
				.economy(RoomTypeUsageRequest.builder().free(0).build())
				.premium(RoomTypeUsageRequest.builder().free(0).build())
				.build();

		// when then
		mockMvc.perform(post("/room-occupancy-optimization/calculation")
						.content(asJsonString(request))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.economy.occupied").value(0))
				.andExpect(jsonPath("$.economy.free").value(0))
				.andExpect(jsonPath("$.economy.totalIncome").value(0))
				.andExpect(jsonPath("$.premium.occupied").value(0))
				.andExpect(jsonPath("$.premium.free").value(0))
				.andExpect(jsonPath("$.premium.totalIncome").value(0));
	}

	@Test
	void shouldFillAllRoomsWhenMoreGuestsThenRooms() throws Exception {
		// given
		RoomOccupancyOptimizationCalculationRequest request = RoomOccupancyOptimizationCalculationRequest
				.builder()
				.economy(RoomTypeUsageRequest.builder().free(3).build())
				.premium(RoomTypeUsageRequest.builder().free(3).build())
				.build();

		// when then
		mockMvc.perform(post("/room-occupancy-optimization/calculation")
						.content(asJsonString(request))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.economy.occupied").value(3))
				.andExpect(jsonPath("$.economy.free").value(0))
				.andExpect(jsonPath("$.economy.totalIncome").value(167))
				.andExpect(jsonPath("$.premium.occupied").value(3))
				.andExpect(jsonPath("$.premium.free").value(0))
				.andExpect(jsonPath("$.premium.totalIncome").value(738));
	}

	@Test
	void shouldFillAllocateAllGuestsWhenMoreRoomsThenGuests() throws Exception {
		// given
		RoomOccupancyOptimizationCalculationRequest request = RoomOccupancyOptimizationCalculationRequest
				.builder()
				.economy(RoomTypeUsageRequest.builder().free(5).build())
				.premium(RoomTypeUsageRequest.builder().free(7).build())
				.build();

		// when then
		mockMvc.perform(post("/room-occupancy-optimization/calculation")
						.content(asJsonString(request))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.economy.occupied").value(4))
				.andExpect(jsonPath("$.economy.free").value(1))
				.andExpect(jsonPath("$.economy.totalIncome").value(189))
				.andExpect(jsonPath("$.premium.occupied").value(6))
				.andExpect(jsonPath("$.premium.free").value(1))
				.andExpect(jsonPath("$.premium.totalIncome").value(1054));
	}

	@Test
	void shouldNotSellEconomyRoomsToHighBudgetGuests() throws Exception {
		// given
		RoomOccupancyOptimizationCalculationRequest request = RoomOccupancyOptimizationCalculationRequest
				.builder()
				.economy(RoomTypeUsageRequest.builder().free(7).build())
				.premium(RoomTypeUsageRequest.builder().free(2).build())
				.build();

		// when then
		mockMvc.perform(post("/room-occupancy-optimization/calculation")
						.content(asJsonString(request))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.economy.occupied").value(4))
				.andExpect(jsonPath("$.economy.free").value(3))
				.andExpect(jsonPath("$.economy.totalIncome").value(189))
				.andExpect(jsonPath("$.premium.occupied").value(2))
				.andExpect(jsonPath("$.premium.free").value(0))
				.andExpect(jsonPath("$.premium.totalIncome").value(583));
	}

	@Test
	void shouldUpgradeBestBudgetGuestsWhenNotEnoughEconomyRooms() throws Exception {
		// given
		RoomOccupancyOptimizationCalculationRequest request = RoomOccupancyOptimizationCalculationRequest
				.builder()
				.economy(RoomTypeUsageRequest.builder().free(1).build())
				.premium(RoomTypeUsageRequest.builder().free(10).build())
				.build();

		// when then
		mockMvc.perform(post("/room-occupancy-optimization/calculation")
						.content(asJsonString(request))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.economy.occupied").value(1))
				.andExpect(jsonPath("$.economy.free").value(0))
				.andExpect(jsonPath("$.economy.totalIncome").value(45))
				.andExpect(jsonPath("$.premium.occupied").value(7))
				.andExpect(jsonPath("$.premium.free").value(3))
				.andExpect(jsonPath("$.premium.totalIncome").value(1153));
	}

	public static String asJsonString(final Object obj) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(obj);
	}

}
