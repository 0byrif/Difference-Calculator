package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;


public class Differ {
    public static String generate(String js1, String js2, String format) throws Exception {
        String p1 = read(js1);
        String p2 = read(js2);

        String p1Format = getFormat(js1);
        String p2Format = getFormat(js2);

        Map<String, Object> json1 = Parser.parserFiletToMap(p1, p1Format);
        Map<String, Object> json2 = Parser.parserFiletToMap(p2, p2Format);

        List<Map<String, Object>> result = DifferMapList.diffList(json1, json2);
        return Format.formatComparisonResult(result, format);
    }


    public static String generate(String js1, String js2) throws Exception {
        return generate(js1, js2, "stylish");
    }

    public static String read(String pathFile) throws IOException {
        Path path = Paths.get(pathFile).toAbsolutePath().normalize();
        return Files.readString(path);
    }

    public static String getFormat(String pathFile) {
        String[] parts = pathFile.split("\\.");
        if (parts.length > 1) {
            String extension = parts[parts.length - 1].toLowerCase();
            return extension;
        } else {
            throw new RuntimeException("Некорректный формат файла");
        }
    }
}
