package com.example.microservice.order.service.service;

import com.example.microservice.order.service.model.Customer;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CustomerService {
    RestTemplate restTemplate;
    private String customerServiceHost = "http://customerservice:9090/customers/";

    public CustomerService() {
        this.restTemplate = new RestTemplate();
    }

    public Customer findById(String id) {
            final String url = customerServiceHost + id;
            return restTemplate.getForObject(url, Customer.class);
    }

    public List<Customer> findByName(String name) {
        final String url = customerServiceHost+ "search-name" + name;
        return Arrays.asList(restTemplate.getForObject(url, Customer[].class));
    }

    public Boolean verifyCustomer(String id){
        final String url = customerServiceHost + "verify/" + id;
        return restTemplate.getForObject(url, Boolean.class);
    }

}
