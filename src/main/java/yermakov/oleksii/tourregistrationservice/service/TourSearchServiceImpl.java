package yermakov.oleksii.tourregistrationservice.service;

import yermakov.oleksii.tourregistrationservice.model.TourDto;

import java.util.List;

public class TourSearchServiceImpl implements TourSearchService {
    @Override
    public List<TourDto> searchByName(String tourName) {
        return List.of();
    }

    @Override
    public List<TourDto> fetchSuggestions(String query) {
        return List.of();
    }
}
