package streams;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DuplicateInList {
    public static void main(String[] args) {


        List<Integer> l = List.of(2,4,3,1,5,7,5,3,9,5,3,7);
        System.out.println("Distinct elements in the list : " +
                    l.stream().distinct().toList());
        Set<Integer> s  = new HashSet<>();
        Set<Integer> set = l.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(n->n.getValue()> 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        System.out.println("Duplicate elements in the list : " +
                set);
        System.out.println("Duplicate elements in the list : " +
                l.stream().filter(n-> !s.add(n)).collect(Collectors.toSet()));

    }
}
