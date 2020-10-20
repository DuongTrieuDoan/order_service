package com.example.microservice.order.service.service;

import com.example.microservice.order.service.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    CustomerService customerService;

    private Customer customer;
    private final String name = UUID.randomUUID().toString();
    private final String id = UUID.randomUUID().toString();
    private final String urlSearch = "http://localhost:9090/customers/" + id;
    private final String urlSearchName = "http://localhost:9090/customers/search-name/" + name;
    private final String urlVerify = "http://localhost:9090/customers/verify/" + id;
    @Before
    public void setUp()  {
        customer = new Customer(id, name);
        Customer[] customers = {customer};
        when(restTemplate.getForObject(urlSearch, Customer.class)).thenReturn(customer);
        when(restTemplate.getForObject(urlSearchName, Customer[].class)).thenReturn(customers);
        when(restTemplate.getForObject(urlVerify, Boolean.class)).thenReturn(true);
    }

    @Test
    public void findById_found_returnCustomer() {
        Customer result = customerService.findById(id);

        assertThat(result, is(customer));
    }

    @Test
    public void findById_notFound_returnEmpty() {
        when(restTemplate.getForObject(urlSearch, Customer.class)).thenReturn(null);
        Customer result = customerService.findById(id);

        assertThat(result, is(false));
    }

    @Test
    public void findByName_found_returnCustomerList() {
        List<Customer> result  = customerService.findByName(name);

        assertThat(result.size(), is(1));
        assertThat(result.get(0), is(customer));
    }

    @Test
    public void findByName_notFound_returnCustomerList() {
        Customer[] customers = {};
        when(restTemplate.getForObject(urlSearchName, Customer[].class)).thenReturn(customers);
        List<Customer> result  = customerService.findByName(name);

        assertThat(result.size(), is(0));
    }

    @Test
    public void verifyCustomer_found_returnTrue() {
        Boolean result = customerService.verifyCustomer(id);

        assertThat(result, is(true));
    }

    @Test
    public void verifyCustomer_notFound_returnFalse() {
        when(restTemplate.getForObject(urlVerify, Boolean.class)).thenReturn(false);
        Boolean result = customerService.verifyCustomer(id);

        assertThat(result, is(false));
    }
}