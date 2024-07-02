package yermakov.oleksii.tourregistrationservice.service;

import yermakov.oleksii.tourregistrationservice.model.SearchQuery;
import yermakov.oleksii.tourregistrationservice.model.TourDto;

import java.util.List;

public interface TourSearchService{
    List<TourDto> search(SearchQuery query);
    List<String> fetchSuggestions(String query);
}
