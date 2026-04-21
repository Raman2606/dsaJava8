package streams;

import streams.data.InitialData;

import java.util.List;
import java.util.stream.Collectors;

public class ComplexStreams {

    public static void main(String[] args) {

        List<InitialData.Customer> customers = InitialData.getCustomers();

//        customers.stream().collect(Collectors.groupingBy(InitialData.Customer::getLocation), Collectors.flatMapping())
    }
}



