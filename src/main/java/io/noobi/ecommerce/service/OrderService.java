package io.noobi.ecommerce.service;

import io.noobi.ecommerce.model.Order;

import java.util.List;

public interface OrderService {

  List<Order> getAllOrders();

  Order getOrderById(Long id);

  void addOrder(Order order);

  boolean updateOrder(Long id,Order order);

  boolean deleteOrder(Long id);

}
