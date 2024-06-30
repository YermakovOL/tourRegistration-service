package yermakov.oleksii.tourregistrationservice.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Iterator;
import java.util.Map;

public class JsonComparison {

    public static boolean isNodeContained(JsonNode node, JsonNode subNode) {
        if (node.equals(subNode)) {
            return true;
        }

        if (node.isObject() && subNode.isObject()) {
            ObjectNode objNode = (ObjectNode) node;
            ObjectNode objSubNode = (ObjectNode) subNode;

            Iterator<Map.Entry<String, JsonNode>> fields = objSubNode.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                if (!objNode.has(field.getKey()) || !isNodeContained(objNode.get(field.getKey()), field.getValue())) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }
}