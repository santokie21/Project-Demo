package io.noobi.ecommerce.service.impl;

import io.noobi.ecommerce.model.Customer;
import io.noobi.ecommerce.model.Order;
import io.noobi.ecommerce.repository.OrderRepository;
import io.noobi.ecommerce.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;

  public OrderServiceImpl(OrderRepository orderRepository) {
    this.orderRepository=orderRepository;
  }

  @Override
  public List<Order> getAllOrders(){
    return orderRepository.findAll();
  }

  @Override
  public Order getOrderById(Long id){
    return orderRepository.findById(id).orElse(null);
  }

  @Override
  public void addOrder(Order order){
    orderRepository.save(order);
  }

  @Override
  public boolean updateOrder(Long id, Order body){
    Order order=getOrderById(id);

    if( order != null ) {

      if(body.getQuantity()>0) order.setQuantity(body.getQuantity());
      if(body.getPrice()>0) order.setPrice(body.getPrice());
      if(body.getTotalPrice()>0) order.setTotalPrice(body.getTotalPrice());
      orderRepository.save(order);

      return true;
    }

    return false;
  }

  @Override
  public boolean deleteOrder(Long id){
    if(orderRepository.findById(id).isPresent()) {
      orderRepository.deleteById(id);
      return true;
    }
    return false;
  }
}
