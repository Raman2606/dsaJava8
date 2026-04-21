package collections.customimplementations;

import java.util.ArrayList;
import java.util.List;

public class TestMyArrayList {
    public static void main(String[] args) {

        MyArrayList<Integer> list1 = new MyArrayList<>();
        List<Integer> l = new ArrayList<>();
        list1.add(4);
        list1.add(5);
        System.out.println(list1);
        list1.forEach(System.out::println);
    }
}
