package com.example.microservice.order.service.repo;

import com.example.microservice.order.service.model.OrderDetail;
import org.springframework.data.repository.CrudRepository;

public interface OrderDetailRepository extends CrudRepository<OrderDetail, String> {
}
