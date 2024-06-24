package yermakov.oleksii.tourregistrationservice.service;

import yermakov.oleksii.tourregistrationservice.model.Tour;
import yermakov.oleksii.tourregistrationservice.model.TourDto;

import java.util.List;

public interface TourSearchService{
    List<TourDto> searchByName(String tourName);
    List<TourDto> fetchSuggestions(String query);
}
