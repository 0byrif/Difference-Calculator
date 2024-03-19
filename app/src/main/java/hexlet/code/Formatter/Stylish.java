package hexlet.code.Formatter;

import java.util.List;
import java.util.Map;

public class Stylish {
    private static final String REMOVED = "  - %s: %s\n";
    private static final String ADDED = "  + %s: %s\n";
    private static final String SAME = "    %s: %s\n";
    private static final String UPDATED = "  - %s: %s\n  + %s: %s\n";

    public static String stylishResult(List<Map<String, Object>> diffList) {
        StringBuilder result = new StringBuilder("{\n");
        for (var element : diffList) {
            switch (element.get("STATUS").toString()) {
                case "REMOVED" -> result.append(String.format(REMOVED,
                        element.get("FIELD"),
                        element.get("VALUE")));
                case "ADDED" -> result.append(String.format(ADDED,
                        element.get("FIELD"),
                        element.get("VALUE")));
                case "SAME" -> result.append(String.format(SAME,
                        element.get("FIELD"),
                        element.get("VALUE")));
                case "UPDATED" -> result.append(String.format(UPDATED,
                        element.get("FIELD"),
                        element.get("VALUE1"),
                        element.get("FIELD"),
                        element.get("VALUE2")));
                default -> throw new RuntimeException("Unexpected status: " + element.get("STATUS"));
            }
        }
        result.append("}");
        return result.toString();
    }
}
