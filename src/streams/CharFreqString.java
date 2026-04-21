package streams;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CharFreqString {

    public static void main(String[] args) {

        String word = "My name is RamAn Garg";
        Map<String, Long> collect = Arrays
                .stream(word.split(""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(collect);
        Map<Character, Long> collect1 = IntStream
                .range(0, word.length())
                .mapToObj(word::charAt)
                .map(Character::toLowerCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(collect1);


    }
}
