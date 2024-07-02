package yermakov.oleksii.tourregistrationservice.service;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Service;
import yermakov.oleksii.tourregistrationservice.model.SearchQuery;
import yermakov.oleksii.tourregistrationservice.model.TourDto;
import yermakov.oleksii.tourregistrationservice.model.document.TourDocument;
import yermakov.oleksii.tourregistrationservice.service.mapper.TourMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TourSearchServiceImpl implements TourSearchService {

    private static final String TOURS_INDEX = "tours";

    private final ElasticsearchOperations operations;

    private final TourMapper tourMapper;

    @Override
    public List<TourDto> search(SearchQuery searchQuery){
        BoolQuery.Builder boolQueryBuilder = QueryBuilders.bool();

        boolQueryBuilder.must(QueryBuilders.multiMatch(builder -> builder
                .query(searchQuery.getQuery())
                .fields("name^3", "description^1")
                .fuzziness("AUTO")));

        if (searchQuery.getMinStartDate() != null || searchQuery.getMaxStartDate() != null) {
            boolQueryBuilder.filter(QueryBuilders.range(builder -> {
                builder.field("start_date");
                Optional.ofNullable(searchQuery.getMinStartDate()).ifPresent(builder::from);
                Optional.ofNullable(searchQuery.getMaxStartDate()).ifPresent(builder::to);
                return builder;
            }));
        }

        if (searchQuery.getMinPrice() != null || searchQuery.getMaxPrice() != null) {
            boolQueryBuilder.filter(QueryBuilders.range(builder -> {
                builder.field("price");
                Optional.ofNullable(searchQuery.getMinPrice()).ifPresent(builder::from);
                Optional.ofNullable(searchQuery.getMaxPrice()).ifPresent(builder::to);
                return builder;
            }));
        }

        BoolQuery boolQuery = boolQueryBuilder.build();
        NativeQuery nativeQuery = NativeQuery.builder()
                .withQuery(boolQuery._toQuery())
                .build();

        SearchHits<TourDocument> searchHits = operations.search(nativeQuery, TourDocument.class, IndexCoordinates.of(TOURS_INDEX));
        List<TourDto> tourMatches = new ArrayList<>();
        searchHits.forEach(searchHit -> tourMatches.add(tourMapper.convert(searchHit.getContent())));
        return tourMatches;
    }


    @Override
    public List<String> fetchSuggestions(String query) {

        Query wildcard = QueryBuilders.wildcard(builder -> builder
                .field("name")
                .value(query.toLowerCase() + "*"));
        NativeQuery searchQuery = NativeQuery.builder()
                .withFilter(wildcard)
                .withPageable(PageRequest.of(0, 5))
                .build();
        SearchHits<TourDocument> searchSuggestions = operations.search(searchQuery, TourDocument.class, IndexCoordinates.of(TOURS_INDEX));
        List<String> suggestions = new ArrayList<>();

        searchSuggestions.getSearchHits().forEach(searchHit -> {
            suggestions.add(searchHit.getContent().getName());
        });
        return suggestions;
    }
}
