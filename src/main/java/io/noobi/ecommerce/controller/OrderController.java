package io.noobi.ecommerce.controller;

import io.noobi.ecommerce.model.Customer;
import io.noobi.ecommerce.model.Order;
import io.noobi.ecommerce.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService=orderService;
  }

  @GetMapping
  public ResponseEntity<List<Order>> getAllOrders() {
    List<Order> orders = orderService.getAllOrders();
    if(orders.isEmpty()) {
      return new ResponseEntity<>(orders, HttpStatus.NO_CONTENT);
    }
    return ResponseEntity.ok(orders);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Order> orderById(@PathVariable Long id) {
    Order order = orderService.getOrderById(id);
    if(order == null) {
      Order dummy = new Order(0L,0,0,0);
      return new ResponseEntity<>(dummy,HttpStatus.NOT_FOUND);
    }
    return ResponseEntity.ok(order);
  }

  @PostMapping
  public ResponseEntity<String> addOrder(@RequestBody Order order) {
    orderService.addOrder(order);
    return new ResponseEntity<>("Order added Successfully!",HttpStatus.CREATED);
  }

  @PostMapping("/{id}")
  public ResponseEntity<String> updateOrder(@PathVariable Long id,@RequestBody Order order) {
    boolean isUpdated = orderService.updateOrder(id,order);
    if(isUpdated) {
      return ResponseEntity.ok("Order updated successfully!");
    }
    return new ResponseEntity<>("No Order available with id : "+id, HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
    boolean isDeleted = orderService.deleteOrder(id);
    if(isDeleted) {
      return ResponseEntity.ok("Order updated successfully!");
    }
    return new ResponseEntity<>("No Order available with id : "+id, HttpStatus.NOT_FOUND);
  }
}
