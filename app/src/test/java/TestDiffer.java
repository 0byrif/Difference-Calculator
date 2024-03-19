import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestDiffer {
    private static String resultStylish;
    private static String resultPlain;
    private static String resultJson;

    public static final Path FILE1_JSON = getFixturePath("testfile1.json");
    public static final Path FILE2_JSON = getFixturePath("testfile2.json");
    public static final Path FILE1_YAML = getFixturePath("test1Y.yml");
    public static final Path FILE2_YAML = getFixturePath("test2Y.yml");

    @BeforeAll
    public static void beforeAll() throws Exception {
        resultStylish = readFixture("resultSlylish.txt");
        resultPlain = readFixture("resultPlain.txt");
        resultJson = readFixture("resultJson.json");
    }

    @Test
    public void testRightComparison() throws Exception {
        String jsonFile = Differ.generate(String.valueOf(FILE1_JSON), String.valueOf(FILE2_JSON));
        String yamlFile = Differ.generate(String.valueOf(FILE1_YAML), String.valueOf(FILE2_YAML));
        String resultDefaultFormatJson = Differ.generate(String.valueOf(FILE1_JSON),
                String.valueOf(FILE2_JSON), "stylish");
        String resultDefaultFormatYaml = Differ.generate(String.valueOf(FILE1_YAML),
                String.valueOf(FILE2_YAML), "stylish");
        String resultPlainFormatJson = Differ.generate(String.valueOf(FILE1_JSON),
                String.valueOf(FILE2_JSON), "plain");
        String resultPlainFormatYaml = Differ.generate(String.valueOf(FILE1_YAML),
                String.valueOf(FILE2_YAML), "plain");
        assertThat(jsonFile).isEqualToIgnoringWhitespace(resultStylish);
        assertThat(yamlFile).isEqualToIgnoringWhitespace(resultStylish);
        assertThat(resultDefaultFormatJson).isEqualToIgnoringWhitespace(resultStylish);
        assertThat(resultDefaultFormatYaml).isEqualToIgnoringWhitespace(resultStylish);
        assertThat(resultPlainFormatJson).isEqualToIgnoringWhitespace(resultPlain);
        assertThat(resultPlainFormatYaml).isEqualToIgnoringWhitespace(resultPlain);
    }

    @Test
    public void testRightComparisonFormatJSONJ() throws Exception {
        String jsonFile = Differ.generate(String.valueOf(FILE1_JSON), String.valueOf(FILE2_JSON), "json");
        String yamlFile = Differ.generate(String.valueOf(FILE1_YAML), String.valueOf(FILE2_YAML), "json");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode expectedJson = objectMapper.readTree(resultJson);
        JsonNode actualJson = objectMapper.readTree(jsonFile);
        JsonNode actualJson1 = objectMapper.readTree(yamlFile);
        assertThat(actualJson).isEqualTo(expectedJson);
        assertThat(actualJson1).isEqualTo(expectedJson);
    }

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath).trim();

    }
}
