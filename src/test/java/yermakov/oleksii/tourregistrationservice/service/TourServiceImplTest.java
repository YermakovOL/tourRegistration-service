package yermakov.oleksii.tourregistrationservice.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import yermakov.oleksii.tourregistrationservice.service.repository.TourRepository;

import static org.junit.jupiter.api.Assertions.*;

class TourServiceImplTest {
    @MockBean
    TourRepository tourRepository;
    TourService tourService = new TourServiceImpl(tourRepository);
    @Test
    void getTours() {

    }

    @Test
    void postTour() {
    }

    @Test
    void deleteTourById() {
    }

    @Test
    void getTourById() {
    }

    @Test
    void putTourById() {
    }

    @Test
    void patchTourById() {
    }
}