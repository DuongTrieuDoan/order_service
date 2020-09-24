package com.example.microservice.order.service.services;

import com.example.microservice.order.service.domain.Customer;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CustomerService {
    RestTemplate restTemplate;
    public CustomerService() {
        this.restTemplate = new RestTemplate();
    }

    public Customer findById(String id) {
        final String url = "http://localhost:9090/customers/" + id + "/search";
        return restTemplate.getForObject(url, Customer.class);
    }

    public List<Customer> findByName(String name) {
        final String url = "http://localhost:9090/customers/" + name + "/search-name";
        return restTemplate.getForObject(url, List.class);
    }

    public Boolean verifyCustomer(String id){
        final String url = "http://localhost:9090/customers/" + id + "/verify";
        return restTemplate.getForObject(url, Boolean.class);
    }

}
