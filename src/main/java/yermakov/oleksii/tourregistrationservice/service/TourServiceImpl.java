package yermakov.oleksii.tourregistrationservice.service;

import lombok.RequiredArgsConstructor;
import yermakov.oleksii.tourregistrationservice.model.TourDto;
import yermakov.oleksii.tourregistrationservice.service.repository.TourRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {
    private final TourRepository tourRepository;
    @Override
    public List<TourDto> getTours(Integer page, Integer size) {
        return null;
    }

    @Override
    public Optional<TourDto> postTour(TourDto tourDto) {
        return Optional.empty();
    }

    @Override
    public Boolean deleteTourById(UUID tourId) {
        return null;
    }

    @Override
    public Optional<TourDto> getTourById(UUID tourId) {
        return Optional.empty();
    }

    @Override
    public Optional<TourDto> putTourById(UUID tourId, TourDto tourDto) {
        return Optional.empty();
    }

    @Override
    public Optional<TourDto> patchTourById(UUID tourId, TourDto tourDto) {
        return Optional.empty();
    }
}
