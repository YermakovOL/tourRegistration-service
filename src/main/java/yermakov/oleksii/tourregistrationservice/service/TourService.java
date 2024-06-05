package yermakov.oleksii.tourregistrationservice.service;

import org.springframework.data.domain.Page;
import yermakov.oleksii.tourregistrationservice.model.TourDto;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

public interface TourService {

    /**
     * Get a paginated list of tours.
     *
     * @param page the page number to retrieve.
     * @param size the number of tours per page.
     * @return a list of tours.
     */
    Page<TourDto> getTours(Integer page, Integer size);

    /**
     * Create a new tour.
     *
     * @param tourDto the tour to create.
     * @return Location of created tour.
     */
    String saveTour(TourDto tourDto);

    /**
     * Delete a tour by its ID.
     *
     * @param tourId the ID of the tour to delete.
     * @return true if the deletion was successful, false otherwise.
     */
    Boolean deleteTourById(UUID tourId);

    /**
     * Get a tour by its ID.
     *
     * @param tourId the ID of the tour to retrieve.
     * @return the retrieved tour wrapped in an Optional.
     */
    Optional<TourDto> getTourById(UUID tourId);

    /**
     * Update a tour by its ID.
     *
     * @param tourId the ID of the tour to update.
     * @param tourDto the updated tour.
     * @return the updated tour wrapped in an Optional.
     */
    Optional<TourDto> updateTourById(UUID tourId, TourDto tourDto);

    /**
     * Partially update a tour by its ID.
     *
     * @param tourId the ID of the tour to update.
     * @param tourDto the tour data to update.
     * @return the updated tour wrapped in an Optional.
     */
    Optional<TourDto> partiallyUpdateTourById(UUID tourId, TourDto tourDto);
}
