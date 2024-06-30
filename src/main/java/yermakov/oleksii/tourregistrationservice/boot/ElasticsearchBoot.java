package yermakov.oleksii.tourregistrationservice.boot;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Component;
import yermakov.oleksii.tourregistrationservice.boot.ex.ElasticsearchBootException;
import yermakov.oleksii.tourregistrationservice.util.JsonComparison;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@Component
@RequiredArgsConstructor
public class ElasticsearchBoot implements CommandLineRunner {
    private final ElasticsearchOperations elasticsearchOperations;
    private final ObjectMapper objectMapper;

    private Map<String, Object> settings;
    private Map<String, Object> mappings;
    private IndexCoordinates index;

    @Override
    public void run(String... args) throws IOException {
        settings = objectMapper.readValue(new File("src/main/resources/db/elasticsearch/tours_index_settings.json"), new HashMap<String, Object>().getClass());
        mappings = objectMapper.readValue(new File("src/main/resources/db/elasticsearch/tours_index_mappings.json"), new HashMap<String, Object>().getClass());

        index = IndexCoordinates.of("tours");

        if (!elasticsearchOperations.indexOps(index).exists()) {
            if(!createToursIndex()) throw new ElasticsearchBootException("tours index does not create");
        } else {
            validateToursIndex();
            log.info("Validation tours index - successful");
        }
    }

    public boolean createToursIndex(){
        return elasticsearchOperations.indexOps(index).create(Document.from(settings), Document.from(mappings));
    }

    private void validateToursIndex() throws ElasticsearchBootException {
        Map<String, Object> gotSettings = elasticsearchOperations.indexOps(index).getSettings();
        Map<String, Object> gotMappings = elasticsearchOperations.indexOps(index).getMapping();

        JsonNode currentSettingsNode = objectMapper.valueToTree(gotSettings);
        JsonNode originalSettingsNode = objectMapper.valueToTree(settings);
        JsonNode currentMappingsNode = objectMapper.valueToTree(gotMappings);
        JsonNode originalMappingsNode = objectMapper.valueToTree(mappings);

        if (!JsonComparison.isNodeContained(currentSettingsNode, originalSettingsNode)) {
            throw new ElasticsearchBootException("Current settings do not match the original settings");
        }
        if (!JsonComparison.isNodeContained(currentMappingsNode, originalMappingsNode)) {
            throw new ElasticsearchBootException("Current mappings do not match the original mappings");
        }
    }
}