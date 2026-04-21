package streams;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Merge2Maps {
    public static void main(String[] args) {
        Map<String, Integer> map1 = Map.of("a", 1, "b", 2);
        Map<String, Integer> map2 = Map.of("b", 3, "c", 4);

        Map<String, Integer> collect = Stream.of(map1, map2)
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Integer::sum));
        System.out.println("merged map : " + collect);
    }
}
