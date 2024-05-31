package yermakov.oleksii.tourregistrationservice.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import yermakov.oleksii.tourregistrationservice.model.Tour;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/tour")
public class TourController implements TourCrudApi {

    @Override
    @GetMapping
    public ResponseEntity<List<Tour>> getTours(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return TourCrudApi.super.getTours(page, size);
    }

    @Override
    @PostMapping
    public ResponseEntity<Tour> postTour(@RequestBody Tour tour) {
        return TourCrudApi.super.postTour(tour);
    }

    @Override
    @DeleteMapping("/{tourId}")
    public ResponseEntity<Void> deleteTourById(@PathVariable("tourId") UUID tourId) {
        return TourCrudApi.super.deleteTourById(tourId);
    }

    @Override
    @GetMapping("/{tourId}")
    public ResponseEntity<Tour> getTourById(@PathVariable("tourId")UUID tourId) {
        return TourCrudApi.super.getTourById(tourId);
    }

    @Override
    @PutMapping("/{tourId}")
    public ResponseEntity<Tour> putTourById(@PathVariable("tourId")UUID tourId, @RequestBody Tour tour) {
        return TourCrudApi.super.putTourById(tourId, tour);
    }

    @Override
    public ResponseEntity<Tour> patchTourById(UUID tourId, Tour tour) {
        return TourCrudApi.super.patchTourById(tourId, tour);
    }
}
