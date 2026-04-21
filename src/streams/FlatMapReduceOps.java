package streams;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FlatMapReduceOps {

    static class Order {
        List<Item> items;
        Long orderId;
        double totalValue;
        private LocalDateTime orderDateTIme;

        public Long getOrderId() {
            return orderId;
        }

        public double getTotalValue() {
            return totalValue;
        }

        public LocalDateTime getOrderDateTIme() {
            return orderDateTIme;
        }
// getters

        public List<Item> getItems() {
            return items;
        }

        public Order(List<Item> items, Long orderId, double totalValue, LocalDateTime orderDateTIme) {
            this.items = items;
            this.orderId = orderId;
            this.totalValue = totalValue;
            this.orderDateTIme = orderDateTIme;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Order order = (Order) o;
            return Objects.equals(items, order.items);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(items);
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }

        @Override
        public String toString() {
            return "Order{" +
                    "items=" + items +
                    '}';
        }
    }
   static  class Item {
        String name;
        double price;
        String category;
        int quantity;
        boolean inStock;

       public int getQuantity() {
           return quantity;
       }

       public boolean isInStock() {
           return inStock;
       }

       // getters

       public Item(String name, double price, String category, int quantity, boolean inStock) {
           this.name = name;
           this.price = price;
           this.category = category;
           this.quantity = quantity;
           this.inStock = inStock;
       }

       public String getCategory() {
           return category;
       }



       public String getName() {
           return name;
       }

       public void setName(String name) {
           this.name = name;
       }

       public double getPrice() {
           return price;
       }

       public void setPrice(double price) {
           this.price = price;
       }

       @Override
       public boolean equals(Object o) {
           if (this == o) return true;
           if (o == null || getClass() != o.getClass()) return false;
           Item item = (Item) o;
           return Double.compare(price, item.price) == 0 && Objects.equals(name, item.name);
       }

       @Override
       public int hashCode() {
           return Objects.hash(name, price);
       }

       @Override
       public String toString() {
           return "Item{" +
                   "name='" + name + '\'' +
                   ", price=" + price +
                   '}';
       }
   }
    public static void main(String[] args) {
        Map<String,String> map1 = new HashMap<>();
//        map1.merge()
        List<Order> orders = getOrdersData();
//        getAllItems(orders);
//        totalPriceOfAllItems(orders);
//        groupByItemNameAndCountOccurrence(orders);
//        List<String> sentences = Arrays.asList("Hello World", "Java Streams are powerful", "Hello Java");
//        System.out.println("sentences : " + sentences);
//        tokenizeAndUniqueLowerCharacter(sentences);
//        longestStringUsingReduce(sentences);
//        categoryBasedProductSoldCount(orders);
        removeDuplicateItemsByName(orders);

    }

    private static void categoryBasedProductSoldCount(List<Order> orders) {

        Predicate<Order> totalPricePredicate = order-> order.getTotalValue()>300.0;
        Predicate<Order>  within24HoursCheck = order -> !order.getOrderDateTIme().isBefore(LocalDateTime.now().minusDays(1));
        Map<String, Integer> categoryBasedProductSoldCount = orders
                .stream()
                .filter(totalPricePredicate.and(within24HoursCheck))
                .flatMap(o -> o.getItems().stream())
                .filter(Item::isInStock)
                .collect(Collectors.groupingBy(Item::getCategory, Collectors.summingInt(Item::getQuantity)))
                        .entrySet()
                        .stream()
                        .sorted(Map.Entry.<String,Integer>comparingByValue(Collections.reverseOrder()))
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1,e2) ->e1,
                                LinkedHashMap::new
                        ));
        System.out.println("categoryBasedProductSoldCount : " + categoryBasedProductSoldCount);
    }

    private static void longestStringUsingReduce(List<String> sentences) {
        String longestWord = sentences.stream().flatMap(s -> Arrays.stream(s.split("\\s+"))).reduce("", (s1, s2) -> s1.length() > s2.length() ? s1 : s2);
        System.out.println("longestWord : " + longestWord);
    }

    private static void tokenizeAndUniqueLowerCharacter(List<String> sentences) {
        List<String> lowerCaseWords = sentences.stream().map(s -> Arrays.asList(s.split(" "))).flatMap(List::stream).filter(word -> word.matches("[a-z]+")).distinct().toList();
        System.out.println("lowerCaseWords : " + lowerCaseWords);
    }

    private static void groupByItemNameAndCountOccurrence(List<Order> orders) {
        Map<String, Long> groupByItemNameAndCountOccurrence = orders.stream().flatMap(o -> o.getItems().stream()).map(Item::getName).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("groupByItemNameAndCountOccurrence : " + groupByItemNameAndCountOccurrence);
    }

    private static void totalPriceOfAllItems(List<Order> orders) {
        double priceSum = orders.stream().map(Order::getItems).flatMap(List::stream).mapToDouble(Item::getPrice).sum();
        System.out.println("priceSum : " + priceSum);
         priceSum = orders.stream().map(Order::getItems).flatMap(List::stream).map(Item::getPrice).reduce(0.0,Double::sum);
        System.out.println("priceSum : " + priceSum);


    }

    private static void getAllItems(List<Order> orders) {
        List<Item> itemsFlattened = orders.stream().map(Order::getItems).flatMap(List::stream).toList();
        System.out.println("itemsFlattened : " + itemsFlattened);
    }

    private static List<Order> getOrdersData() {
        List<Order> orders = Arrays.asList(
                new Order(Arrays.asList(
                        new Item("Laptop", 1200.00, "Electronics", 10, true),
                        new Item("Mouse", 1200.00, "Electronics", 10, true),
                        new Item("Mouse", 25.00, "Accessories", 50, true),
                        new Item("Laptop", 25.00, "Accessories", 50, true)
                ), 1L, 1225.00, LocalDateTime.now()),
                new Order(Arrays.asList(
                        new Item("Phone", 800.00, "Electronics", 5, true),
                        new Item("Charger", 20.00, "Accessories", 100, true)
                ), 2L, 820.00, LocalDateTime.now()),
                new Order(Arrays.asList(
                        new Item("Tablet", 300.00, "Electronics", 8, false),
                        new Item("Case", 15.00, "Accessories", 200, true)
                ), 3L, 315.00, LocalDateTime.now())
        );
        return orders;
    }


    /***
     * remove duplicates on the basis of name
     *
     */
    private static void removeDuplicateItemsByName(List<Order> orders) {

        Set<String> uniqueItemNames = new HashSet<>();
        Set<String> collect = orders.stream()
                .map(Order::getItems)
                .flatMap(List::stream)
                .map(Item::getName)
                .filter(name -> !uniqueItemNames.add(name))
                .collect((Collectors.toSet()));
        System.out.println(collect);

    }
}
