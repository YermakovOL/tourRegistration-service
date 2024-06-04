package yermakov.oleksii.tourregistrationservice.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yermakov.oleksii.tourregistrationservice.api.exception.NotFoundException;
import yermakov.oleksii.tourregistrationservice.model.TourDto;
import yermakov.oleksii.tourregistrationservice.service.TourService;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tour")
public class TourController implements TourCrudApi {

    private final TourService tourService;

    @Override
    @GetMapping
    public ResponseEntity<Page<TourDto>> getTours(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResponseEntity.ok(tourService.getTours(page, size));
    }

    @Override
    @PostMapping
    public ResponseEntity<URI> postTour(@RequestBody TourDto tourDto) {
        return ResponseEntity.created(tourService.saveTour(tourDto)).build();
    }

    @Override
    @DeleteMapping("/{tourId}")
    public ResponseEntity<Void> deleteTourById(@PathVariable("tourId") UUID tourId) {
        if(!tourService.deleteTourById(tourId)) throw new NotFoundException();
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/{tourId}")
    public ResponseEntity<TourDto> getTourById(@PathVariable("tourId")UUID tourId) {
        Optional<TourDto> tourById = tourService.getTourById(tourId);
        if(tourById.isEmpty()) throw new NotFoundException();
        return ResponseEntity.ok(tourById.get());
    }

    @Override
    @PutMapping("/{tourId}")
    public ResponseEntity<TourDto> putTourById(@PathVariable("tourId")UUID tourId, @RequestBody TourDto tourDto) {
        if(tourService.updateTourById(tourId,tourDto).isEmpty()) throw new NotFoundException();
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<TourDto> patchTourById(UUID tourId, TourDto tourDto) {
        if(tourService.partiallyUpdateTourById(tourId,tourDto).isEmpty()) throw new NotFoundException();
        return ResponseEntity.noContent().build();
    }
}
