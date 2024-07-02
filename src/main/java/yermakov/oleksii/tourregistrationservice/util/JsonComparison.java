package yermakov.oleksii.tourregistrationservice.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Iterator;

public class JsonComparison {

    public static boolean isNodeContained(JsonNode node, JsonNode subNode) {
        if (node.equals(subNode)) {
            return true;
        }

        if (node.isObject() && subNode.isObject()) {
            ObjectNode objNode = (ObjectNode) node;
            Iterator<JsonNode> fields = objNode.elements();
            for (Iterator<JsonNode> it = fields; it.hasNext(); ) {
                JsonNode json = it.next();
                if(json.equals(subNode)) return true;
                else return isNodeContained(json,subNode);
            }
        }
        return false;
    }
}