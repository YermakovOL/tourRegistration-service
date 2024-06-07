package yermakov.oleksii.tourregistrationservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import yermakov.oleksii.tourregistrationservice.api.TourController;
import yermakov.oleksii.tourregistrationservice.model.TourDto;
import yermakov.oleksii.tourregistrationservice.service.TourService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TourController.class)
class TourControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // For JSON serialization/deserialization

    @MockBean
    private TourService tourService;

    @Test
    void getTours_returnsListOfTours() throws Exception {
        Page<TourDto> tours = new PageImpl<>(List.of(new TourDto()), PageRequest.of(0, 10), 1);
        when(tourService.getTours(0, 10)).thenReturn(tours);

        mockMvc.perform(get(TourController.TOUR_API_PATH))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(1));
    }

    @Test
    void postTour_createsNewTour() throws Exception {
        String tourId = UUID.randomUUID().toString();
        TourDto tourDto = getExpTourDto();
        when(tourService.saveTour(tourDto)).thenReturn(tourId);

        mockMvc.perform(post(TourController.TOUR_API_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tourDto)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", TourController.TOUR_API_PATH + "/" + tourId));
    }

    @Test
    void getTourById_whenTourNotFound_returnsNotFound() throws Exception {
        UUID tourId = UUID.randomUUID();
        when(tourService.getTourById(tourId)).thenReturn(Optional.empty());

        mockMvc.perform(get(TourController.TOUR_API_PATH + "/{tourId}", tourId))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteTourById_whenTourExists_deletesTour() throws Exception {
        UUID tourId = UUID.randomUUID();
        when(tourService.deleteTourById(tourId)).thenReturn(true);

        mockMvc.perform(delete(TourController.TOUR_API_PATH + "/{tourId}", tourId))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteTourById_whenTourNotFound_returnsNotFound() throws Exception {
        UUID tourId = UUID.randomUUID();
        when(tourService.deleteTourById(tourId)).thenReturn(false);

        mockMvc.perform(delete(TourController.TOUR_API_PATH + "/{tourId}", tourId))
                .andExpect(status().isNotFound());
    }

    @Test
    void getTourById_whenTourExists_returnsTour() throws Exception {
        UUID tourId = UUID.randomUUID();
        TourDto tourDto = new TourDto();
        when(tourService.getTourById(tourId)).thenReturn(Optional.of(tourDto));

        mockMvc.perform(get(TourController.TOUR_API_PATH + "/{tourId}", tourId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").exists()); // Verify some JSON is returned
    }


    @Test
    void putTourById_whenTourExists_updatesTour() throws Exception {
        UUID tourId = UUID.randomUUID();
        TourDto updatedTourDto = getExpTourDto();
        when(tourService.updateTourById(eq(tourId), any(TourDto.class))).thenReturn(Optional.of(updatedTourDto));

        mockMvc.perform(put(TourController.TOUR_API_PATH + "/{tourId}", tourId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedTourDto)))
                .andExpect(status().isNoContent());
    }

    @Test
    void putTourById_whenTourNotFound_returnsNotFound() throws Exception {
        UUID tourId = UUID.randomUUID();
        TourDto updatedTourDto = getExpTourDto();
        when(tourService.updateTourById(eq(tourId), any(TourDto.class))).thenReturn(Optional.empty());

        mockMvc.perform(put(TourController.TOUR_API_PATH + "/{tourId}", tourId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedTourDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void patchTourById_whenTourExists_partiallyUpdatesTour() throws Exception {
        UUID tourId = UUID.randomUUID();
        TourDto patchTourDto = getExpTourDto(); // Partial update data
        when(tourService.partiallyUpdateTourById(eq(tourId), any(TourDto.class))).thenReturn(Optional.of(patchTourDto));

        mockMvc.perform(patch(TourController.TOUR_API_PATH + "/{tourId}", tourId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patchTourDto)))
                .andExpect(status().isNoContent());
    }

    @Test
    void patchTourById_whenTourNotFound_returnsNotFound() throws Exception {
        UUID tourId = UUID.randomUUID();
        TourDto patchTourDto = getExpTourDto(); // Partial update data
        when(tourService.partiallyUpdateTourById(eq(tourId), any(TourDto.class))).thenReturn(Optional.empty());

        mockMvc.perform(patch(TourController.TOUR_API_PATH + "/{tourId}", tourId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patchTourDto)))
                .andExpect(status().isNotFound());
    }

    private TourDto getExpTourDto() {
        return  TourDto.builder()
                .id(UUID.randomUUID())
                .name("Tour to Paris")
                .description("A wonderful tour to Paris")
                .price(BigDecimal.valueOf(1000))
                .startDate(LocalDate.of(2024, 7, 1))
                .endDate(LocalDate.of(2024, 7, 10))
                .build();
    }
}
