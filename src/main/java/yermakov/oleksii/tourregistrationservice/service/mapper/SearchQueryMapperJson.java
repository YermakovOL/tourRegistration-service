package yermakov.oleksii.tourregistrationservice.service.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yermakov.oleksii.tourregistrationservice.model.SearchQuery;
import yermakov.oleksii.tourregistrationservice.model.TourDto;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Component
public class SearchQueryMapperJson implements SearchQueryMapper {
    private final ObjectMapper objectMapper;

    @Override
    public SearchQuery readBytes(byte[] searchQuery) throws IOException {
        return objectMapper.readValue(searchQuery, SearchQuery.class);
    }

    @Override
    public byte[] convertQuery(SearchQuery searchQuery) {
        try {
            return objectMapper.writeValueAsBytes(searchQuery);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Wrong json",e);
        }
    }

    @Override
    public byte[] convertQueryResult(List<TourDto> result){
        try {
            return objectMapper.writeValueAsBytes(result);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Wrong json",e);
        }
    }
}
