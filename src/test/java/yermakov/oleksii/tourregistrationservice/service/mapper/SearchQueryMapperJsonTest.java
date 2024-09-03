package yermakov.oleksii.tourregistrationservice.service.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import yermakov.oleksii.tourregistrationservice.model.SearchQuery;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class SearchQueryMapperJsonTest {
    SearchQueryMapperJson queryMapperJson = new SearchQueryMapperJson(new ObjectMapper());
    public static final String SEARCH_QUERY_JSON = "{\n" +
            "    \"query\": \"mountain hiking\",\n" +
            "    \"minStartDate\": \"2023-08-01\",\n" +
            "    \"maxStartDate\": \"2023-08-31\",\n" +
            "    \"minPrice\": \"100.0\",\n" +
            "    \"maxPrice\": \"500.0\"\n" +
            "}";

    @Test
    void mapString() throws IOException {
        SearchQuery exp = new SearchQuery("mountain hiking", "2023-08-01", "2023-08-31", "100.0", "500.0");
        SearchQuery mappingResult = queryMapperJson.readBytes(SEARCH_QUERY_JSON.getBytes());
        assertThat(mappingResult).isEqualTo(exp);
    }
}