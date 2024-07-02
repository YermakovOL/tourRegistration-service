package yermakov.oleksii.tourregistrationservice.service;

import yermakov.oleksii.tourregistrationservice.model.TourDto;

import java.util.List;

public interface TourSearchService{
    List<TourDto> searchByMultiMatchText(String searchString);
    List<TourDto> fetchSuggestions(String query);
}
