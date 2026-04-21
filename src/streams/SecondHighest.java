package streams;

import java.util.Comparator;
import java.util.List;

public class SecondHighest {
    public static void main(String[] args) {
        List<Integer> l = List.of(2,4,3,1,5,7,5,3,9,5,3,7);
        System.out.println("Second Smallest : " +
                l.stream().sorted().skip(1).findFirst().orElseThrow(() -> new RuntimeException("No second smallest element found")));
        System.out.println("Second Highest : " +
                l.stream().sorted(Comparator.reverseOrder()).skip(1).findFirst().orElseThrow(() -> new RuntimeException("No second highest element found")));

    }
}
