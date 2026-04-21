package streams;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SlidingWindowStream {

    public static void main(String[] args) {
        List<Integer> data = List.of(10, 12, 11, 50, 13, 12);

        int windowSize = 3;
        List<List<Integer>> windows = IntStream
                .range(windowSize, data.size())
                .mapToObj(i -> data.subList(i - windowSize, i))
                .toList();
        System.out.println("windows : " + windows);
        List<Integer> windowSum = IntStream
                .range(windowSize, data.size())
                .mapToObj(i -> data.subList(i - windowSize, i))
                .map(n-> n.stream().mapToInt(Integer::intValue).sum())
                .toList();
        System.out.println("window sum : " + windowSum);
        List<Integer> windowMax = IntStream
                .range(windowSize, data.size())
                .mapToObj(i -> data.subList(i - windowSize, i))
                .map(n -> n.stream().mapToInt(Integer::intValue).max().orElse(0))
                .toList();
        System.out.println("window max : " + windowMax);
    }
}
