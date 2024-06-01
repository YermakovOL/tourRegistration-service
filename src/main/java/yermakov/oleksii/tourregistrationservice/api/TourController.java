package yermakov.oleksii.tourregistrationservice.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yermakov.oleksii.tourregistrationservice.model.TourDto;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tour")
public class TourController implements TourCrudApi {

    @Override
    @GetMapping
    public ResponseEntity<List<TourDto>> getTours(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return TourCrudApi.super.getTours(page, size);
    }

    @Override
    @PostMapping
    public ResponseEntity<TourDto> postTour(@RequestBody TourDto tourDto) {
        return TourCrudApi.super.postTour(tourDto);
    }

    @Override
    @DeleteMapping("/{tourId}")
    public ResponseEntity<Void> deleteTourById(@PathVariable("tourId") UUID tourId) {
        return TourCrudApi.super.deleteTourById(tourId);
    }

    @Override
    @GetMapping("/{tourId}")
    public ResponseEntity<TourDto> getTourById(@PathVariable("tourId")UUID tourId) {
        return TourCrudApi.super.getTourById(tourId);
    }

    @Override
    @PutMapping("/{tourId}")
    public ResponseEntity<TourDto> putTourById(@PathVariable("tourId")UUID tourId, @RequestBody TourDto tourDto) {
        return TourCrudApi.super.putTourById(tourId, tourDto);
    }

    @Override
    public ResponseEntity<TourDto> patchTourById(UUID tourId, TourDto tourDto) {
        return
                TourCrudApi.super.patchTourById(tourId, tourDto);
    }
}
