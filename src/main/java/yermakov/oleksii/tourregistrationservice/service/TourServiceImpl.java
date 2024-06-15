package yermakov.oleksii.tourregistrationservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import yermakov.oleksii.tourregistrationservice.model.Tour;
import yermakov.oleksii.tourregistrationservice.model.TourDto;
import yermakov.oleksii.tourregistrationservice.service.mapper.TourMapper;
import yermakov.oleksii.tourregistrationservice.service.repository.TourRepository;

import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;
    private final TourMapper tourMapper;

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_PAGE_SIZE = 25;

    @Override
    public Page<TourDto> getTours(Integer page, Integer size) {
        return tourRepository.findAll(buildPageRequest(page,size))
                .map(tourMapper::convertTourIntoDto);
    }
    public PageRequest buildPageRequest(Integer pageNumber, Integer pageSize) {
        int queryPageNumber;
        int queryPageSize;

        if (pageNumber != null && pageNumber > 0) {
            queryPageNumber = pageNumber - 1;
        } else {
            queryPageNumber = DEFAULT_PAGE;
        }

        if (pageSize == null) {
            queryPageSize = DEFAULT_PAGE_SIZE;
        } else {
            if (pageSize > 1000) {
                queryPageSize = 1000;
            } else {
                queryPageSize = pageSize;
            }
        }

        return PageRequest.of(queryPageNumber, queryPageSize);
    }

    @Override
    public String saveTour(TourDto tourDto) {
        Tour save = tourRepository.save(tourMapper.convertDtoIntoTour(tourDto));
        return save.getId().toString();
    }

    @Override
    public Boolean deleteTourById(UUID tourId) {
        if (tourRepository.findById(tourId).isEmpty()) return false;
        tourRepository.deleteById(tourId);
        return true;
    }

    @Override
    public Optional<TourDto> getTourById(UUID tourId) {
        Optional<Tour> byId = tourRepository.findById(tourId);
        if (byId.isEmpty()) return Optional.empty();
        else return byId.map(tourMapper::convertTourIntoDto);
    }

    @Override
    public Optional<TourDto> updateTourById(UUID tourId, TourDto tourDto) {
        return tourRepository.findById(tourId).map(tour -> {
            tour.setName(tourDto.getName());
            tour.setDescription(tourDto.getDescription());
            tour.setPrice(tourDto.getPrice());
            tour.setStartDate(tourDto.getStartDate());
            tour.setEndDate(tourDto.getEndDate());
            Tour savedTour = tourRepository.save(tour);
            return tourMapper.convertTourIntoDto(savedTour);
        });
    }

    @Override
    public Optional<TourDto> partiallyUpdateTourById(UUID tourId, TourDto tourDto) {
        return tourRepository.findById(tourId).map(tour -> {
            if (!isNull(tourDto.getName())) {
                tour.setName(tourDto.getName());
            }
            if (!isNull(tourDto.getDescription())) {
                tour.setDescription(tourDto.getDescription());
            }
            if (!isNull(tourDto.getPrice())) {
                tour.setPrice(tourDto.getPrice());
            }
            if (!isNull(tourDto.getStartDate())) {
                tour.setStartDate(tourDto.getStartDate());
            }
            if (!isNull(tourDto.getEndDate())) {
                tour.setEndDate(tourDto.getEndDate());
            }

            // Сохраняем обновленный объект в базе данных
            Tour savedTour = tourRepository.save(tour);
            return tourMapper.convertTourIntoDto(savedTour); // Возвращаем обновленный DTO
        });
    }
}
