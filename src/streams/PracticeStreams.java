package streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PracticeStreams {
    public static void main(String[] args) {

        List<String> input = Arrays.asList("A", "B", "C", "A", "D", "E", "F", "B", "G", "H");
        System.out.println("Input Data : " + input);
        Map<String, Integer> scores = Map.of("John", 90, "Alex", 80, "Ema", 95);
// Sort by value descending


//        findFrequency(input);
//        removeDuplicatesWithOrderPreserved(input);
//        distinctElementIndexes(input);
//        firstOccurrenceOfEachCharacter(input);
//        mostFrequentlyOccurringWord(input);
//        leastFrequentlyOccurringWord(input);


        System.out.println("Initial map : " + scores);
        sortMapBasedOnValue(scores);
        List<String> names = Arrays.asList("john", "jane", "jack", "doe", "LoganPaul");
        List<Integer> numbers = Arrays.asList(1,22,222,4,5,3,2,5,1,111,11,4,4,6);
        System.out.println("Initial map : " + names);
        upperCaseAndFilter(names);
        upperCaseFirstChar(names);
        alternateCharUpperCase(names);
        System.out.println("longestString : " + longestString(names).get());
        System.out.println("numberStartingWith : " + numberStartingWith(numbers, 1));

    }

    private static List<Integer> numberStartingWith(List<Integer> numbers, int i) {
        return numbers.stream().map(String::valueOf).filter(s->s.startsWith(String.valueOf(i))).mapToInt(Integer::parseInt).boxed().toList();
    }

    private static Optional<String> longestString(List<String> names) {
      return names.stream().max(Comparator.comparing(String::length));
    }

    private static void alternateCharUpperCase(List<String> names) {

                String input = "alternatecase";
                String result = IntStream.range(0, input.length())
                        .mapToObj(i -> i % 2 == 0
                                ? Character.toUpperCase(input.charAt(i))
                                : Character.toLowerCase(input.charAt(i)))
                        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                        .toString();

                System.out.println("alternatecase : " + result);

    }

    private static void upperCaseFirstChar(List<String> names) {
        List<String> op = names.stream().filter(n -> n.toLowerCase().startsWith("j")).map(n-> n.substring(0,1).toUpperCase().concat(n.substring(1))).toList();
        System.out.println("Start with J first char  of word capital : " + op);
    }

    private static void upperCaseAndFilter(List<String> names) {

        List<String> op =names.stream().filter(n->n.toLowerCase().startsWith("j")).map(String::toUpperCase).toList();
        System.out.println("Start with J and uppercase"+op);
    }

    private static void sortMapBasedOnValue(Map<String, Integer> scores) {
        Map<String, Integer> sortedMap = scores
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (old, newv) ->old,
                        LinkedHashMap::new

                        ));
        System.out.println("Map on ASC order of values : " + sortedMap);
        Map<String, Integer> sortedMapDesc = scores
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (old, newv) ->old,
                        LinkedHashMap::new

                ));
        System.out.println("Map on DESC order of values : " + sortedMapDesc);
        Comparator<String> compareString = String::compareTo;
    }

    private static void findFrequency(List<String> input) {

        Map<String, Long> freqMap = input
                .stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("Freq map : " + freqMap);
    }

    private static void leastFrequentlyOccurringWord(List<String> names) {

        String leastFrequent = names
                .stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
        System.out.println("Least frequent : " + leastFrequent);
    }

    private static void mostFrequentlyOccurringWord(List<String> names) {
        String frequentCharacter  = names.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        System.out.println("Most frequent : " + frequentCharacter);
    }

    private static void firstOccurrenceOfEachCharacter(List<String> names) {
        Map<String, Integer> firstOcccurrenceMap = IntStream.range(0, names.size())
                .boxed()
                .collect(Collectors.toMap(
                        names::get,
                        i -> i,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
        System.out.println("firstOcccurrenceMap" + firstOcccurrenceMap);

    }

    /**
     * Remove duplicates and preserve insertion order
     */
    private static void removeDuplicatesWithOrderPreserved(List<String> names) {
        /*
         Using set

        return new LinkedHashSet<>(names)
         */

        /*
        Without extra m/y O(n2)

        List<String> uNames = IntStream.range(0, names.size())
                .filter(i-> names.indexOf(names.get(i)) == i)
                .mapToObj(names::get)
                .toList();
         */


        //Using map
        List<String> uNames = IntStream.range(0, names.size())
                .boxed()
                .collect(Collectors.toMap(
                        names::get, // Key: element
                        i -> i,     // Value: index
                        (existing, replacement) -> existing, // Merge function (keep first occurrence)
                        LinkedHashMap::new // Maintain insertion order
                ))
                .keySet()
                .stream()
                .toList();

        System.out.println("Duplicates Removed : " + uNames);
    }


    /**
     * Indexes of only unique elements
     */
    private static void distinctElementIndexes(List<String> names) {
        List<Integer> op = new ArrayList<>();
        Set<String> unique = new HashSet<>();
        // Remove duplicates and preserve insertion order
//        return new LinkedHashSet<>(names)
        List<Integer> uNames = IntStream.range(0, names.size())
                .filter(i-> unique.add(names.get(i)))
                .boxed()
                .toList();
        System.out.println("Unique Element indexes " + uNames);
        op = IntStream.range(0, names.size())
                .filter(i-> names.indexOf(names.get(i)) ==i)
                .boxed()
                .toList();
        System.out.println("Unique Element indexes " + op);
    }
}
