package hexlet.code;

import hexlet.code.Formatter.Json;
import hexlet.code.Formatter.Plain;
import hexlet.code.Formatter.Stylish;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;

public class Format {
    public static String formatComparisonResult(List<Map<String, Object>> diffList, String format)
            throws JsonProcessingException {
        return switch (format) {
            case "stylish" -> Stylish.stylishResult(diffList);
            case "plain" -> Plain.plainResult(diffList);
            case "json" -> Json.jsonResult(diffList);
            default -> throw new RuntimeException("Unexpected format: " + format);
        };
    }
}

