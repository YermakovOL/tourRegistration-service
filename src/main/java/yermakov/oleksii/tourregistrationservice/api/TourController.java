package yermakov.oleksii.tourregistrationservice.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yermakov.oleksii.tourregistrationservice.api.exception.NotFoundException;
import yermakov.oleksii.tourregistrationservice.model.TourDto;
import yermakov.oleksii.tourregistrationservice.service.TourService;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(TourController.TOUR_API_PATH)
public class TourController implements TourCrudApi {

    public static final String TOUR_API_PATH = "/tourCrud";
    public static final String TOUR_ID_PATH = "/{tourId}";
    private final TourService tourService;

    @Override
    @PostMapping
    public ResponseEntity<URI> postTour(@RequestBody TourDto tourDto) {
        return ResponseEntity.created(URI.create(TOUR_API_PATH + "/" + tourService.saveTour(tourDto))).build();
    }

    @Override
    @DeleteMapping(TOUR_ID_PATH)
    public ResponseEntity<Void> deleteTourById(@PathVariable("tourId") UUID tourId) {
        if (!tourService.deleteTourById(tourId)) throw new NotFoundException();
        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping(TOUR_ID_PATH)
    public ResponseEntity<TourDto> putTourById(@PathVariable("tourId") UUID tourId, @RequestBody TourDto tourDto) {
        if (tourService.updateTourById(tourId, tourDto).isEmpty()) throw new NotFoundException();
        return ResponseEntity.noContent().build();
    }

    @Override
    @PatchMapping(TOUR_ID_PATH)
    public ResponseEntity<TourDto> patchTourById(@PathVariable("tourId") UUID tourId, @RequestBody TourDto tourDto) {
        if (tourService.partiallyUpdateTourById(tourId, tourDto).isEmpty()) throw new NotFoundException();
        return ResponseEntity.noContent().build();
    }
}
