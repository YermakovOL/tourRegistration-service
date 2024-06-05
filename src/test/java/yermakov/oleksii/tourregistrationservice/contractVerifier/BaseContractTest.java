package yermakov.oleksii.tourregistrationservice.contractVerifier;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class BaseContractTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    TourService tourService;
    @Autowired
    TourController tourController;

    @BeforeEach
    public void setUp() {

        // Настройка RestAssuredMockMvc для использования контроллера
        RestAssuredMockMvc.standaloneSetup(tourController);

        // Пример замоканных данных
        TourDto sampleTour = new TourDto(UUID.randomUUID(), "Tour 1", "Description 1", new BigDecimal(100), LocalDate.parse("2024-06-01"), LocalDate.parse("2024-06-10"));
        Page<TourDto> tourPage = new PageImpl<>(List.of(sampleTour), PageRequest.of(0, 10), 1);

        // Мокируем метод getTours
        given(tourService.getTours(any(), any())).willReturn(tourPage);

        // Мокируем метод saveTour
        given(tourService.saveTour(any(TourDto.class))).willReturn(sampleTour.getId().toString());

        // Мокируем метод deleteTourById
        given(tourService.deleteTourById(any(UUID.class))).willReturn(true);

        // Мокируем метод getTourById
        given(tourService.getTourById(any(UUID.class))).willReturn(Optional.of(sampleTour));

        // Мокируем метод updateTourById
        given(tourService.updateTourById(any(UUID.class), any(TourDto.class))).willReturn(Optional.of(sampleTour));

        // Мокируем метод partiallyUpdateTourById
        given(tourService.partiallyUpdateTourById(any(UUID.class), any(TourDto.class))).willReturn(Optional.of(sampleTour));
    }
}