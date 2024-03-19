package hexlet.code.Formatter;

import java.util.List;
import java.util.Map;

public class Plain {
    private static final String REMOVED = "Property '%s' was removed\n";
    private static final String ADDED = "Property '%s' was added with value: %s\n";
    private static final String UPDATED = "Property '%s' was updated. From %s to %s\n";

    public static String plainResult(List<Map<String, Object>> diffList) {
        StringBuilder result = new StringBuilder();
        for (var element : diffList) {
            switch (element.get("STATUS").toString()) {
                case "REMOVED" -> result.append(String.format(REMOVED,
                        element.get("FIELD")));
                case "ADDED" -> result.append(String.format(ADDED,
                        element.get("FIELD"),
                        processingComplexValue(element.get("VALUE"))));
                case "SAME" -> { }
                case "UPDATED" -> result.append(String.format(UPDATED,
                        element.get("FIELD"),
                        processingComplexValue(element.get("VALUE1")),
                        processingComplexValue(element.get("VALUE2"))));
                default -> throw new RuntimeException("Unexpected status: " + element.get("STATUS"));
            }
        }
        return result.substring(0, result.length() - 1);
    }

    public static String processingComplexValue(Object object) {
        boolean isComplexValue = object instanceof Map<?, ?> || object instanceof List<?>;
        boolean isStringObject = object instanceof String;
        if (isComplexValue) {
            return "[complex value]";
        } else if (isStringObject) {
            return String.format("'%s'", object);
        } else {
            return String.valueOf(object);
        }
    }
}
