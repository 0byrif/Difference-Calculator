package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parserFiletToMap(String content, String inputFormat)
            throws JsonProcessingException {
        switch (inputFormat) {
            case "json" -> {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(content, new TypeReference<>() { });
            }
            case "yml" -> {
                ObjectMapper mapper = new YAMLMapper();
                return mapper.readValue(content, new TypeReference<>() { });
            }
            default -> throw new RuntimeException("Unexpected format: " + inputFormat);
        }
    }
}

