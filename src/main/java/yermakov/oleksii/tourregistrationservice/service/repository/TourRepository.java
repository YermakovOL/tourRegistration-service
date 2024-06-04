package yermakov.oleksii.tourregistrationservice.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yermakov.oleksii.tourregistrationservice.model.Tour;

import java.util.UUID;

public interface TourRepository extends JpaRepository<Tour, UUID> {
}
