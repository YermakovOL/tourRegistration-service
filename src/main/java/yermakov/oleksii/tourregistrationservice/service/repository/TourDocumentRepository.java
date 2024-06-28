package yermakov.oleksii.tourregistrationservice.service.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import yermakov.oleksii.tourregistrationservice.model.document.TourDocument;

import java.util.UUID;
@Repository
public interface TourDocumentRepository extends ElasticsearchRepository<TourDocument, UUID> {
}
