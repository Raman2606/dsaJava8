package dsa.arrays;

import java.util.*;
import java.util.stream.Collectors;

public class GroupAnagrams {
    public static void main(String[] args) {

        var words  = Arrays.asList("ab", "ba", "cd","dc");
        var anagramMap = new HashMap<String, List<String>>();
        System.out.println(words);
        for( String word: words) {
            char[] charArray = word.toCharArray();
            Arrays.sort(charArray);
            var newWord = new String(charArray);
            anagramMap.computeIfAbsent(newWord, key-> new ArrayList<>()).add(word);

        }
        System.out.println(anagramMap.values());

        //using java 8
        Map<String, List<String>> collect = words.stream()
//                .map(String::toCharArray)
//                .sorted()
                .collect(Collectors.groupingBy(GroupAnagrams::sortChars));
        System.out.println(collect.values());
    }
    private static String sortChars(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
