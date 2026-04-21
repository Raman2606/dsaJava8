package streams;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class StreamGroupingBy {
    static class Book {
        String title;
        String genre;

        public Book(String number, String dystopian) {
            this.title   = number;
            this.genre = dystopian;
        }
        public String getGenre() { return genre; }
        public String getTitle() { return title; }
        @Override
        public String toString() {
            return genre + "-" + title;
        }
    }
    static class Person {
        String name;
        String city;
        Double salary;

        Person(String name, String city, double salary) {
            this.name = name;
            this.city = city;
            this.salary = salary;
        }

        public String getCity() { return city; }
        public String getName() { return name; }
        public Double getSalary() { return salary; }
        @Override
        public String toString() {
            return name + "-" + city;
        }
    }

    /**
     * Demo entry point.
     *
     * Creates sample lists of words, numbers, people, and books, then calls the various
     * grouping methods below and prints their results. This method is for demonstrating
     * different stream grouping patterns.
     *
     * Example (partial) output:
     *   initial data : [a, apple, bat, ball, cat]
     *   Grouped by length : {1=[a], 3=[bat, cat], 4=[ball], 5=[apple]}
     *   groupByLengthAndCount : {1=1, 3=2, 4=1, 5=1}
     *   group by odd/even : {EVEN=[2, 4, 6], ODD=[1, 3, 5]}
     *   groupByFirstChar : {a=[a, apple], b=[bat, ball], c=[cat]}
     *   groupBYFirstCharAndSort : {a=[a, apple], b=[ball, bat], c=[cat]}
     *   groupBySalaryBracket : {OK=[Alice-Delhi, Bob-Mumbai, Charlie-Delhi], GOOD=[David-Mumbai], VERY GOOD=[Eva-Delhi]}
     *   groupByCityAnsSalaryBracket : {Delhi={OK=[Alice-Delhi, Charlie-Delhi], VERY GOOD=[Eva-Delhi]}, Mumbai={OK=[Bob-Mumbai], GOOD=[David-Mumbai]}}
     *   groupPeopleByCity : {Delhi=[Alice-Delhi, Charlie-Delhi, Eva-Delhi], Mumbai=[Bob-Mumbai, David-Mumbai]}
     *   groupPeopleByCityAndCount : {Delhi=3, Mumbai=2}
     *   groupByCityAndLongestName : {Delhi=Optional[Charlie-Delhi], Mumbai=Optional[David-Mumbai]}
     *   groupByGenreAndJoinTitles : {Dystopian=1984, Brave New World, Fantasy=The Hobbit, LOTR}
     *   nthHighestSalary : Optional[David-Mumbai]
     */
    public static void main(String[] args) {
        List<String> words = Arrays.asList("a", "apple", "bat", "ball", "cat");
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Person> people = Arrays.asList(
                new Person("Alice", "Delhi" ,100.00),
                new Person("Bob", "Mumbai", 1000.00),
                new Person("Charlie", "Delhi", 50.00),
                new Person("David", "Mumbai",1500.00),
                new Person("Eva", "Delhi", 2000.00)
        );
        System.out.println("initial data : " + words);
        groupByLength(words);
        groupByLengthAndCount(words);
        groupByEvenOdd(nums);
        groupByFirstChar(words);
        groupBYFirstCharAndSort(words);
        groupBySalaryBracket(people);
        groupByCityAnsSalaryBracket(people);
//        groupBYFirstCharAndCollec(words);

        System.out.println("   \n\n * * * * * * *\n   CUSTOM CLASS OP \n* * * * * * *");
        System.out.println("People : " + people);
        groupPeopleByCity(people);
        groupPeopleByCityAndCount(people);
        groupByCityAndLongestName(people);

        List<Book> books = Arrays.asList(
                new Book("1984", "Dystopian"),
                new Book("Brave New World", "Dystopian"),
                new Book("The Hobbit", "Fantasy"),
                new Book("LOTR", "Fantasy")
        );
        groupByGenreAndJoinTitles(books);
        System.out.println("nthHighestSalary : " + nthHighestSalary(people, 2));


    }

    /**
     * Returns the n-th highest salary person from the given list.
     *
     * Implementation:
     * - Sorts people by salary in descending order.
     * - Skips (n - 1) persons.
     * - Returns the next one as an Optional.
     *
     * Example input:
     *   people = [Alice(100), Bob(1000), Charlie(50), David(1500), Eva(2000)]
     *   n = 2
     *
     * Example output:
     *   Optional[David-Mumbai]  // 2nd highest salary is David (1500)
     */
    private static Optional<Person> nthHighestSalary(List<Person> people, int n) {
        return people.stream().sorted(Comparator.comparing(Person::getSalary).reversed()).skip(n -1).findFirst();
    }

    /**
     * Groups people by city and, within each city, by salary bracket.
     *
     * Salary brackets:
     *   - "OK"        if salary <= 1000.00
     *   - "GOOD"      if salary <= 1500.00 (and > 1000.00)
     *   - "VERY GOOD" if salary  > 1500.00
     *
     * Prints a nested map:
     *   Map<city, Map<bracket, List<Person>>>
     *
     * Example input:
     *   people = [Alice-Delhi(100), Bob-Mumbai(1000), Charlie-Delhi(50), David-Mumbai(1500), Eva-Delhi(2000)]
     *
     * Example output:
     *   groupByCityAnsSalaryBracket : {Delhi={OK=[Alice-Delhi, Charlie-Delhi], VERY GOOD=[Eva-Delhi]}, Mumbai={OK=[Bob-Mumbai], GOOD=[David-Mumbai]}}
     */
    private static void groupByCityAnsSalaryBracket(List<Person> people) {
        Map<String, Map<String, List<Person>>> groupByCityAnsSalaryBracket = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getCity,
                        Collectors.collectingAndThen(
                                toList(),
                                list1 -> list1.stream()
                                        .collect(Collectors.groupingBy(
                                                p -> p.getSalary() <= 1000.00 ?
                                                        "OK" : (p.getSalary() <= 1500.00 ?
                                                        "GOOD" : "VERY GOOD"))))));


        System.out.println("groupByCityAnsSalaryBracket : " + groupByCityAnsSalaryBracket);
    }

    /**
     * Groups all people into salary brackets, ignoring city.
     *
     * Salary brackets:
     *   - "OK"        if salary <= 1000.00
     *   - "GOOD"      if salary <= 1500.00 (and > 1000.00)
     *   - "VERY GOOD" if salary  > 1500.00
     *
     * Prints:
     *   Map<bracket, List<Person>>
     *
     * Example input:
     *   people = [Alice(100), Bob(1000), Charlie(50), David(1500), Eva(2000)]
     *
     * Example output:
     *   groupBySalaryBracket : {OK=[Alice-Delhi, Bob-Mumbai, Charlie-Delhi], GOOD=[David-Mumbai], VERY GOOD=[Eva-Delhi]}
     */
    private static void groupBySalaryBracket(List<Person> people) {
        Map<String, List<Person>> groupBySalaryBracket = people.stream().collect(Collectors.groupingBy(p -> p.getSalary() <= 1000.00 ? "OK" : (p.getSalary() <= 1500.00 ? "GOOD" : "VERY GOOD")));
        System.out.println("groupBySalaryBracket : " + groupBySalaryBracket);
    }

    /**
     * Groups books by genre and joins their titles into a comma-separated string per genre.
     *
     * Prints:
     *   Map<genre, "title1, title2, ...">
     *
     * Example input:
     *   books = [("1984", "Dystopian"), ("Brave New World", "Dystopian"), ("The Hobbit", "Fantasy"), ("LOTR", "Fantasy")]
     *
     * Example output:
     *   groupByGenreAndJoinTitles : {Dystopian=1984, Brave New World, Fantasy=The Hobbit, LOTR}
     */
    private static void groupByGenreAndJoinTitles(List<Book> books) {
        Map<String, String> groupByGenreAndJoinTitles = books.stream()
                .collect(Collectors.groupingBy(Book::getGenre,
                        Collectors.mapping(Book::getTitle, Collectors.joining(", "))));
        System.out.println("groupByGenreAndJoinTitles : " + groupByGenreAndJoinTitles);
    }

    /**
     * For each city, finds the person whose name has the maximum length.
     *
     * Uses groupingBy on city and maxBy on name length.
     * Prints:
     *   Map<city, Optional<PersonWithLongestName>>
     *
     * Example input:
     *   people = [Alice-Delhi, Bob-Mumbai, Charlie-Delhi, David-Mumbai, Eva-Delhi]
     *
     * Example output:
     *   groupByCityAndLongestName : {Delhi=Optional[Charlie-Delhi], Mumbai=Optional[David-Mumbai]}
     */
    private static void groupByCityAndLongestName(List<Person> people) {
//        Map<String, String> groupByCityAndLongestName = people
//                .stream()
//                .collect(Collectors.groupingBy(Person::getCity,
//                        Collectors.collectingAndThen(
//                                Collectors.toList(),
//                                list -> list
//                                        .stream()
//                                        .map(Person::getName)
//                                        .max(Comparator.comparing(String::length)).orElse(null))));
        Map<String, Optional<Person>> groupByCityAndLongestName = people
                .stream()
                .collect(Collectors.groupingBy(Person::getCity,
                        Collectors.maxBy(Comparator.comparingInt(p->p.getName().length()))
                                )
                );


        System.out.println("groupByCityAndLongestName : " + groupByCityAndLongestName);
    }

    /**
     * Groups words by their first character, then sorts the words alphabetically within each group.
     *
     * Prints:
     *   Map<firstChar, List<sortedWords>>
     *
     * Example input:
     *   words = ["a", "apple", "bat", "ball", "cat"]
     *
     * Example output:
     *   groupBYFirstCharAndSort : {a=[a, apple], b=[ball, bat], c=[cat]}
     */
    private static void groupBYFirstCharAndSort(List<String> words) {
        Map<Character, List<String>> groupBYFirstCharAndSort = words.stream().collect(Collectors.groupingBy(n -> n.charAt(0), Collectors.collectingAndThen(toList(), list -> list.stream().sorted().toList())));

        System.out.println("groupBYFirstCharAndSort : " + groupBYFirstCharAndSort);
    }

    /**
     * Counts how many people live in each city.
     *
     * Prints:
     *   Map<city, count>
     *
     * Example input:
     *   people = [Alice-Delhi, Bob-Mumbai, Charlie-Delhi, David-Mumbai, Eva-Delhi]
     *
     * Example output:
     *   groupPeopleByCityAndCount : {Delhi=3, Mumbai=2}
     */
    private static void groupPeopleByCityAndCount(List<Person> people) {
        Map<String, Long> groupPeopleByCityAndCount = people.stream().collect(Collectors.groupingBy(Person::getCity, Collectors.counting()));
        System.out.println("groupPeopleByCityAndCount : " + groupPeopleByCityAndCount);
    }

    /**
     * Groups people by their city.
     *
     * Prints:
     *   Map<city, List<Person>>
     *
     * Example input:
     *   people = [Alice-Delhi, Bob-Mumbai, Charlie-Delhi, David-Mumbai, Eva-Delhi]
     *
     * Example output:
     *   groupPeopleByCity : {Delhi=[Alice-Delhi, Charlie-Delhi, Eva-Delhi], Mumbai=[Bob-Mumbai, David-Mumbai]}
     */
    private static void groupPeopleByCity(List<Person> people) {
        Map<String, List<Person>> groupPeopleByCity = people.stream().collect(Collectors.groupingBy(Person::getCity));
        System.out.println("groupPeopleByCity : " + groupPeopleByCity);
    }

    /**
     * Groups words by their length and counts how many words have each length.
     *
     * Prints:
     *   Map<length, count>
     *
     * Example input:
     *   words = ["a", "apple", "bat", "ball", "cat"]
     *
     * Example output:
     *   groupByLengthAndCount : {1=1, 3=2, 4=1, 5=1}
     */
    private static void groupByLengthAndCount(List<String> words) {
        Map<Integer, Long> groupByLengthAndCount = words.stream().collect(Collectors.groupingBy(String::length, Collectors.counting()));
        System.out.println("groupByLengthAndCount : " + groupByLengthAndCount);
    }


    /**
     * Groups words by their first character.
     *
     * Prints:
     *   Map<firstChar, List<words>>
     *
     * Example input:
     *   words = ["a", "apple", "bat", "ball", "cat"]
     *
     * Example output:
     *   groupByFirstChar : {a=[a, apple], b=[bat, ball], c=[cat]}
     */
    private static void groupByFirstChar(List<String> words) {
        Map<Character, List<String>> groupByFirstChar = words.stream().collect(Collectors.groupingBy(n-> (char) n.charAt(0)));

        System.out.println("groupByFirstChar : " + groupByFirstChar);
    }

    /**
     * Groups integers into "EVEN" and "ODD" buckets.
     *
     * Prints:
     *   Map<"EVEN"/"ODD", List<Integer>>
     *
     * Example input:
     *   nums = [1, 2, 3, 4, 5, 6]
     *
     * Example output:
     *   group by odd/even : {EVEN=[2, 4, 6], ODD=[1, 3, 5]}
     */
    private static void groupByEvenOdd(List<Integer> words) {
        Map<String, List<Integer>> groupByOddEven = words.stream().collect(Collectors.groupingBy(n-> n%2==0? "EVEN" : "ODD"));
        System.out.println("group by odd/even : " + groupByOddEven);
    }

    /**
     * Groups words by their length.
     *
     * Prints:
     *   Map<length, List<words>>
     *
     * Example input:
     *   words = ["a", "apple", "bat", "ball", "cat"]
     *
     * Example output:
     *   Grouped by length : {1=[a], 3=[bat, cat], 4=[ball], 5=[apple]}
     */
    private static void groupByLength(List<String> words) {
        Map<Integer, List<String>> groupByLength = words.stream().collect(Collectors.groupingBy(String::length));

        System.out.println("Grouped by length : " + groupByLength);
    }
}
