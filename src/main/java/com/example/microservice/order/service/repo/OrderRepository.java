package com.example.microservice.order.service.repo;

import com.example.microservice.order.service.model.Order;
import com.example.microservice.order.service.model.OrderQuery;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public interface OrderRepository extends CrudRepository<Order, String> {
    default List<Order> findByQuery(OrderQuery orderQuery){
        return StreamSupport.stream(this.findAll().spliterator(), false)
                            .filter(order -> orderQuery.customerId().isPresent()? orderQuery.customerId().get().equals(order.getCustomerId()):true
                                    && orderQuery.orderId().isPresent()? orderQuery.orderId().get().equals(order.getId()):true
                                    && orderQuery.orderItemName().isPresent()? order.getOrderDetailList().stream()
                                                                                    .anyMatch(orderDetail -> orderDetail.getName().equals(orderQuery.orderItemName())):true
                                    )
                            .collect(Collectors.toList());
    }
}
