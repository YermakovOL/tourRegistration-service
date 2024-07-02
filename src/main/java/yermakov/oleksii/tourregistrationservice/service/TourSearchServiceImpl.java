package yermakov.oleksii.tourregistrationservice.service;

import co.elastic.clients.elasticsearch._types.query_dsl.MultiMatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Service;
import yermakov.oleksii.tourregistrationservice.model.TourDto;
import yermakov.oleksii.tourregistrationservice.model.document.TourDocument;
import yermakov.oleksii.tourregistrationservice.service.mapper.TourMapper;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Service
public class TourSearchServiceImpl implements TourSearchService {
    private static final String TOURS_INDEX = "tours";
    private final ElasticsearchOperations operations;
    private final TourMapper tourMapper;
    @Override
    public List<TourDto> searchByMultiMatchText(String tourName) {

        MultiMatchQuery multiMatchQuery = QueryBuilders.multiMatch()
                .query(tourName)
                .fields("name^3","description^1")
                .fuzziness("AUTO")
                .build();

        NativeQuery searchQuery = NativeQuery.builder()
                .withQuery(multiMatchQuery._toQuery())
                .build();

        SearchHits<TourDocument> searchHits = operations.search(searchQuery, TourDocument.class, IndexCoordinates.of(TOURS_INDEX));
        List<TourDto> tourMatches = new ArrayList<>();
        searchHits.forEach(searchHit -> tourMatches.add(tourMapper.convert(searchHit.getContent())));
        return tourMatches;
    }

    @Override
    public List<TourDto> fetchSuggestions(String query) {
        return List.of();
    }
}
