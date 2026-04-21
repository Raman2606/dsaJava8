package streams;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FirstNonREpeatingChar {

    public static void main(String[] args) {
//        Find the first non-repeating character
        String word = "My name is RamAn Garg";

        String s = Arrays
                .stream(word.split(""))
                .filter(w -> !w.equals(" "))
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1L)
                .map(Map.Entry::getKey)
                .findFirst().orElseThrow();
        System.out.println(s);
//


    }
}

