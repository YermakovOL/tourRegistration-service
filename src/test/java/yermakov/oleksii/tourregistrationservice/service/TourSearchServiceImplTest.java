package yermakov.oleksii.tourregistrationservice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import yermakov.oleksii.tourregistrationservice.model.SearchQuery;
import yermakov.oleksii.tourregistrationservice.model.TourDto;

import java.util.List;

@SpringBootTest
class TourSearchServiceImplTest {
    @Autowired
    TourSearchService searchService;

    @Test
    public void testSearchByQuery() {
        SearchQuery searchQuery = new SearchQuery("Rome", null, null, null, null);
        List<TourDto> result = searchService.search(searchQuery);
        System.out.println("Search by query results:");
        result.forEach(System.out::println);
    }

    @Test
    public void testSearchByDateRange() {
        SearchQuery searchQuery = new SearchQuery("Tour", "2024-07-01", null, null, null);
        List<TourDto> result = searchService.search(searchQuery);
        System.out.println("Search by date range results:");
        result.forEach(System.out::println);
    }

    @Test
    public void testSearchByPriceRange() {
        SearchQuery searchQuery = new SearchQuery("Tour", null, null, "1000", null);
        List<TourDto> result = searchService.search(searchQuery);
        System.out.println("Search by price range results:");
        result.forEach(System.out::println);
    }

    @Test
    public void testSearchByAllCriteria() {
        SearchQuery searchQuery = new SearchQuery("Tour", "2024-08-01", "2024-08-31", "1100", "1700");
        List<TourDto> result = searchService.search(searchQuery);
        System.out.println("Search by all criteria results:");
        result.forEach(System.out::println);
    }

    @Test
    void fetchSuggestionsTest(){
        System.out.println(searchService.fetchSuggestions("to"));
    }
}