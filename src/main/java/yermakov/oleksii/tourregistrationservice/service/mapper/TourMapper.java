package yermakov.oleksii.tourregistrationservice.service.mapper;

import org.mapstruct.Mapper;
import yermakov.oleksii.tourregistrationservice.model.Tour;
import yermakov.oleksii.tourregistrationservice.model.TourDto;

@Mapper
public interface TourMapper {
    TourDto convertTourIntoDto(Tour tour);
    Tour convertDtoIntoTour(TourDto tourDto);
}
