package yermakov.oleksii.tourregistrationservice.service.mapper;

import yermakov.oleksii.tourregistrationservice.model.SearchQuery;
import yermakov.oleksii.tourregistrationservice.model.TourDto;

import java.io.IOException;
import java.util.List;

public interface SearchQueryMapper {
    SearchQuery readBytes(byte[] searchQuery) throws IOException;
    byte[] convertQuery(SearchQuery searchQuery);
    byte[] convertQueryResult(List<TourDto> result);
}