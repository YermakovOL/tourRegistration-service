package yermakov.oleksii.tourregistrationservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import yermakov.oleksii.tourregistrationservice.api.TourController;
import yermakov.oleksii.tourregistrationservice.config.SpringSecTestConfig;
import yermakov.oleksii.tourregistrationservice.model.TourDto;
import yermakov.oleksii.tourregistrationservice.service.TourService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TourController.class)
@Import(SpringSecTestConfig.class)
class TourControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TourService tourService;

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
        return TourDto.builder()
                .id(UUID.randomUUID())
                .name("Tour to Paris")
                .description("A wonderful tour to Paris")
                .price(BigDecimal.valueOf(1000))
                .startDate(LocalDate.of(2024, 7, 1))
                .endDate(LocalDate.of(2024, 7, 10))
                .build();
    }
}
