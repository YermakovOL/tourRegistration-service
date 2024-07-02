package yermakov.oleksii.tourregistrationservice.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Iterator;
import java.util.Map;

public class JsonComparison {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static boolean isNodeContained(JsonNode node, JsonNode subNode) {
        try {
            if (node.equals(subNode)) {
                return true;
            }

            if (node.isObject() && subNode.isObject()) {

                ObjectNode objNode = (ObjectNode) node;
                Iterator<Map.Entry<String, JsonNode>> fields = objNode.fields();

                for (Iterator<Map.Entry<String, JsonNode>> it = fields; it.hasNext(); ) {

                    Map.Entry<String, JsonNode> next = it.next();

                    String key = next.getKey();
                    JsonNode value = next.getValue();
                    String jsonBranch = "{\"" + key + "\":" + value.toString() + "}";

                    if (objectMapper.readTree(jsonBranch).equals(subNode)) return true;

                    if (value.fields().hasNext()) {
                        if (isNodeContained(value, subNode)) return true;
                    }
                }
            }
            return false;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}