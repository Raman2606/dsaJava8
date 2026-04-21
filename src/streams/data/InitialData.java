package streams.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InitialData {
    public static class Customer {
        private String name;
        private String location;
        private List<Order> orders;

        public Customer(String name, String location, List<Order> orders) {
            this.name = name;
            this.location = location;
            this.orders = orders;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public List<Order> getOrders() {
            return orders;
        }

        public void setOrders(List<Order> orders) {
            this.orders = orders;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Customer)) return false;
            Customer customer = (Customer) o;
            return Objects.equals(name, customer.name) &&
                    Objects.equals(location, customer.location) &&
                    Objects.equals(orders, customer.orders);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, location, orders);
        }
    }

    public static class Order {
        private int year;
        private List<Item> items;

        public Order(int year, List<Item> items) {
            this.year = year;
            this.items = items;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public List<Item> getItems() {
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Order)) return false;
            Order order = (Order) o;
            return year == order.year &&
                    Objects.equals(items, order.items);
        }

        @Override
        public int hashCode() {
            return Objects.hash(year, items);
        }
    }

    public static class Item {
        private String name;
        private String category;
        private double price;

        public Item(String name, String category, double price) {
            this.name = name;
            this.category = category;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
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
            if (!(o instanceof Item)) return false;
            Item item = (Item) o;
            return Double.compare(item.price, price) == 0 &&
                    Objects.equals(name, item.name) &&
                    Objects.equals(category, item.category);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, category, price);
        }
    }

    public static List<Customer> getCustomers() {
        List<InitialData.Customer> customers = new ArrayList<>();

        customers.add(new InitialData.Customer("Alice", "New York", List.of(
                new InitialData.Order(2021, List.of(
                        new InitialData.Item("Book", "Books", 15.99),
                        new InitialData.Item("Headphones", "Electronics", 49.99)
                )),
                new InitialData.Order(2022, List.of(
                        new InitialData.Item("Laptop", "Electronics", 799.99)
                ))
        )));

        customers.add(new InitialData.Customer("Bob", "Los Angeles", List.of(
                new InitialData.Order(2023, List.of(
                        new InitialData.Item("Camera", "Electronics", 299.99),
                        new InitialData.Item("Tripod", "Electronics", 39.99)
                ))
        )));

        customers.add(new InitialData.Customer("Carol", "Chicago", List.of(
                new InitialData.Order(2020, List.of(
                        new InitialData.Item("Notebook", "Books", 5.49),
                        new InitialData.Item("Pen", "Stationery", 1.99)
                )),
                new InitialData.Order(2021, List.of(
                        new InitialData.Item("Backpack", "Accessories", 39.99)
                ))
        )));

        customers.add(new InitialData.Customer("David", "Houston", List.of(
                new InitialData.Order(2022, List.of(
                        new InitialData.Item("Tablet", "Electronics", 199.99)
                ))
        )));

        customers.add(new InitialData.Customer("Eve", "Phoenix", List.of(
                new InitialData.Order(2023, List.of(
                        new InitialData.Item("eReader", "Electronics", 129.99),
                        new InitialData.Item("Charger", "Accessories", 19.99)
                ))
        )));

        customers.add(new InitialData.Customer("Frank", "Philadelphia", List.of(
                new InitialData.Order(2020, List.of(
                        new InitialData.Item("Shoes", "Clothing", 59.99)
                ))
        )));

        customers.add(new InitialData.Customer("Grace", "San Antonio", List.of(
                new InitialData.Order(2021, List.of(
                        new InitialData.Item("Dress", "Clothing", 89.99),
                        new InitialData.Item("Bag", "Accessories", 49.99)
                ))
        )));

        customers.add(new InitialData.Customer("Heidi", "San Diego", List.of(
                new InitialData.Order(2022, List.of(
                        new InitialData.Item("Desk Lamp", "Home", 24.99)
                )),
                new InitialData.Order(2023, List.of(
                        new InitialData.Item("Chair", "Home", 89.99)
                ))
        )));

        customers.add(new InitialData.Customer("Ivan", "Dallas", List.of(
                new InitialData.Order(2020, List.of(
                        new InitialData.Item("Blender", "Kitchen", 34.99),
                        new InitialData.Item("Toaster", "Kitchen", 29.99)
                ))
        )));

        customers.add(new InitialData.Customer("Judy", "San Jose", List.of(
                new InitialData.Order(2021, List.of(
                        new InitialData.Item("Smartphone", "Electronics", 699.99),
                        new InitialData.Item("Case", "Accessories", 19.99)
                ))
        )));

        return customers;

    }
}
