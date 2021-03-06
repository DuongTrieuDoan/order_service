package com.example.microservice.order.service.services;

import com.example.microservice.order.service.domain.Order;
import com.example.microservice.order.service.domain.OrderDetail;
import com.example.microservice.order.service.domain.OrderQuery;
import com.example.microservice.order.service.repo.OrderDetailRepository;
import com.example.microservice.order.service.repo.OrderRepository;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @Mock
    CustomerService customerService;

    @Mock
    OrderRepository orderRepository;

    @Mock
    OrderDetailRepository orderDetailRepository;

    @InjectMocks
    OrderService orderService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Captor
    ArgumentCaptor<Order> orderArgumentCaptor;

    @Captor
    ArgumentCaptor<Iterable<OrderDetail>> orderDetailsArgumentCaptor;

    private Order order;
    private List<Order> orderList;
    private final String id = UUID.randomUUID().toString();
    private final String customerId = UUID.randomUUID().toString();

    @Before
    public void seTup(){
        order = new Order(
                     id,
                    Lists.newArrayList(new OrderDetail()),
                     100L,
                     customerId);

        order.setOrderDetailList(Lists.newArrayList(new OrderDetail(UUID.randomUUID().toString(),
                                                                    UUID.randomUUID().toString(),
                                                                    10,
                                                                    10L,
                                                                    100L,
                                                                    order)));
        orderList = Lists.newArrayList(order);

        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));
        when(customerService.verifyCustomer(customerId)).thenReturn(true);
        when(orderRepository.findByQuery(OrderQuery.builder().customerId(Optional.of(customerId)).build())).thenReturn(orderList);
    }

    @Test
    public void findById_whenFound_returnOrder() {
        Optional<Order> result = orderService.findById(order.getId());

        assertThat(result.isPresent(), is(true));
        assertThat(result.get().getId(), is(order.getId()));
        assertThat(result.get().getCustomerId(), is(order.getCustomerId()));
        assertThat(result.get().getTotalAmount(), is(order.getTotalAmount()));
        assertThat(matchAnyOrderDetail(result.get().getOrderDetailList(), order.getOrderDetailList()), is(true));
    }

    @Test
    public void findById_whenNotFound_returnException() {
        when(orderRepository.findById(order.getId())).thenReturn(Optional.empty());

        Optional<Order> result = orderService.findById(order.getId());

        assertThat(result.isPresent(), is(false));
    }

    @Test
    public void findByCustomerId_whenFound_returnOrderList() {
        List<Order> result = orderService.findByCustomerId(customerId);

        assertThat(result.size(), is(1));
    }

    @Test
    public void findByCustomerId_whenFound_throwException() {
        when(customerService.verifyCustomer(customerId)).thenReturn(false);
        expectedException.expect(EntityNotFoundException.class);
        expectedException.expectMessage(String.format("Customer with id %s is not found", customerId));

        orderService.findByCustomerId(customerId);
    }

    @Test
    public void findByQuery_whenFound_returnOrderList() {
        when(orderRepository.findByQuery(any())).thenReturn(orderList);

        List<Order> result = orderService.findByQuery(OrderQuery.builder().build());

        assertThat(result.size(), is(1));
        assertThat(result.get(0).getId(), is(order.getId()));
        assertThat(result.get(0).getCustomerId(), is(order.getCustomerId()));
        assertThat(result.get(0).getTotalAmount(), is(order.getTotalAmount()));
        assertThat(matchAnyOrderDetail(result.get(0).getOrderDetailList(), order.getOrderDetailList()), is(true));
    }

  @Test
    public void findByQuery_whenNotFound_returnEmptyList() {
      when(orderRepository.findByQuery(any())).thenReturn(Collections.EMPTY_LIST);

      List<Order> result = orderService.findByQuery(OrderQuery.builder().build());

      assertThat(result.isEmpty(), is(true));
    }

    @Test
    public void saveOrder_saveCorrectOrder() {
        orderService.saveOrder(order);

        verify(orderRepository).save(orderArgumentCaptor.capture());
        assertThat(orderArgumentCaptor.getValue().getId(), is(order.getId()));
        assertThat(orderArgumentCaptor.getValue().getCustomerId(), is(order.getCustomerId()));
        assertThat(orderArgumentCaptor.getValue().getTotalAmount(), is(order.getTotalAmount()));
        assertThat(matchAnyOrderDetail(orderArgumentCaptor.getValue().getOrderDetailList(), order.getOrderDetailList()), is(true));
    }

    @Test
    public void saveOrder_saveCorrectOrderDetail(){
        orderService.saveOrder(order);

        verify(orderDetailRepository).saveAll(orderDetailsArgumentCaptor.capture());
        assertThat(matchAnyOrderDetail(StreamSupport.stream(orderDetailsArgumentCaptor.getValue().spliterator(),false)
                                                    .collect(Collectors.toList()),
                                       order.getOrderDetailList()),
                   is(true));
    }

    @Test
    public void saveOrder_whenOrderNotExisting_throwException() {
        when(orderRepository.findById(order.getId())).thenReturn(Optional.empty());
        expectedException.expect(EntityNotFoundException.class);
        expectedException.expectMessage(String.format("Order with id %s is not found", order.getId()));

        orderService.saveOrder(order);
    }

    @Test
    public void createOrder_returnOrderWithId() {
        orderService.createOrder(order);

        verify(orderRepository).save(orderArgumentCaptor.capture());
        assertThat(orderArgumentCaptor.getValue().getId(), is(order.getId()));
        assertThat(orderArgumentCaptor.getValue().getCustomerId(), is(order.getCustomerId()));
        assertThat(orderArgumentCaptor.getValue().getTotalAmount(), is(order.getTotalAmount()));
        assertThat(matchAnyOrderDetail(orderArgumentCaptor.getValue().getOrderDetailList(), order.getOrderDetailList()), is(true));
    }

    @Test
    public void createOrder_saveCorrectOrderDetail(){
        orderService.createOrder(order);

        verify(orderDetailRepository).saveAll(orderDetailsArgumentCaptor.capture());
        assertThat(matchAnyOrderDetail(StreamSupport.stream(orderDetailsArgumentCaptor.getValue().spliterator(),false)
                                                    .collect(Collectors.toList()),
                                       order.getOrderDetailList()),
                   is(true));
    }

    @Test
    public void createOrder_withCustomerNotExistingInDatabase_throwException() {
        when(customerService.verifyCustomer(customerId)).thenReturn(false);
        expectedException.expect(EntityNotFoundException.class);
        expectedException.expectMessage(String.format("Customer with id %s is not found", customerId));

        orderService.createOrder(order);
    }

    private boolean matchAnyOrderDetail(List<OrderDetail> orderDetailList1, List<OrderDetail> orderDetailList2) {
        return orderDetailList1.stream()
                               .anyMatch(rd -> orderDetailList2.stream()
                                                               .anyMatch(d -> d.getCode().equals(rd.getCode())
                                                                       & d.getId().equals(rd.getId())
                                                                       & d.getName().equals(rd.getName())
                                                                       & d.getPrice().equals(rd.getPrice())
                                                                       & (d.getQuantity() == rd.getQuantity())
                                                                       & d.getTotalAmount().equals(rd.getTotalAmount())));
    }
}