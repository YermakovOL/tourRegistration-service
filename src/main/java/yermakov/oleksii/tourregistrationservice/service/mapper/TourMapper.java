package yermakov.oleksii.tourregistrationservice.service.mapper;

import org.mapstruct.Mapper;
import yermakov.oleksii.tourregistrationservice.model.Tour;
import yermakov.oleksii.tourregistrationservice.model.TourDto;
import yermakov.oleksii.tourregistrationservice.model.document.TourDocument;

@Mapper
public interface TourMapper {
    TourDto convert(Tour tour);
    Tour convertIntoEntity(TourDto tourDto);
    TourDto convert(TourDocument tourDocument);
    TourDocument convertIntoDocument(TourDto tourDto);
}
