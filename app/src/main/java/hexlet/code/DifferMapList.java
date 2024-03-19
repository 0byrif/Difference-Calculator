package hexlet.code;

import java.util.Objects;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DifferMapList {
    public static List<Map<String, Object>> diffList(Map<String, Object> map1, Map<String, Object> map2) {
        List<Map<String, Object>> diffList = new ArrayList<>();
        TreeSet<String> keySet = collectKeysInSet(map1, map2);
        for (var key : keySet) {
            Map<String, Object> diffMap = new HashMap<>();

            if (!map2.containsKey(key)) {
                diffMap.put("FIELD", key);
                diffMap.put("STATUS", "REMOVED");
                diffMap.put("VALUE", map1.get(key));
            } else if (!map1.containsKey(key)) {
                diffMap.put("FIELD", key);
                diffMap.put("STATUS", "ADDED");
                diffMap.put("VALUE", map2.get(key));
            } else if (Objects.equals(map1.get(key), map2.get(key))) {
                diffMap.put("FIELD", key);
                diffMap.put("STATUS", "SAME");
                diffMap.put("VALUE", map1.get(key));
            } else {
                diffMap.put("FIELD", key);
                diffMap.put("STATUS", "UPDATED");
                diffMap.put("VALUE1", map1.get(key));
                diffMap.put("VALUE2", map2.get(key));
            }
            diffList.add(diffMap);
        }
        return diffList;
    }

    public static TreeSet<String> collectKeysInSet(Map<String, Object> map1, Map<String, Object> map2) {
        return Stream.of(map1, map2)
                .flatMap(m -> m.keySet().stream())
                .collect(Collectors.toCollection(TreeSet::new));
    }
}
