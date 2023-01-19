package com.kc.cloud.labs.aws.lambda.customers.services;

import com.kc.cloud.labs.aws.lambda.customers.models.Customer;

import java.util.List;

public class CustomerService {
    
    private final static List<Customer> customersRepository = List.of(
            new Customer(1, "John Doe", true),
            new Customer(2, "Jane Salah", false),
            new Customer(3, "Kevin Johnson", true),
            new Customer(4, "Luis Smith", false),
            new Customer(5, "Jack Spark", true)
    );

    public static List<Customer> getAllCustomers() {
        return customersRepository;
    }

    public static Customer getCustomerById(Integer id) {
        return customersRepository.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public static Customer createCustomer(String customerJson) {
        //customer.fromJSON(customerJson);
        return new Customer(10, "Vale Rickson", false);
    }

}
