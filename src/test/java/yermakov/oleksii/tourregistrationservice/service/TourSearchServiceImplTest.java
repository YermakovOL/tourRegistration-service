package yermakov.oleksii.tourregistrationservice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import yermakov.oleksii.tourregistrationservice.model.TourDto;

import java.util.List;

@SpringBootTest
class TourSearchServiceImplTest {
    @Autowired
    TourSearchService searchService;

    @Test
    void searchByMultiMatchText() {
        List<TourDto> tour = searchService.searchByMultiMatchText("Paris");
        System.out.println(tour);
    }
}