package io.noobi.ecommerce.controller;

import io.noobi.ecommerce.model.Customer;
import io.noobi.ecommerce.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping
  public ResponseEntity<List<Customer>> getAllCustomer() {
    List<Customer> customers=customerService.getAllCustomer();
    if(customers.isEmpty()) {
      return new ResponseEntity<>(customers,HttpStatus.NO_CONTENT);
    }
    return ResponseEntity.ok(customers);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Customer> customerById(@PathVariable Long id) {
    Customer customer = customerService.getCustomerById(id);
    if( customer == null ) {
      Customer dummy = new Customer(0L,"un","known",1234567890L,"unknown@example.com");
      return new ResponseEntity<>(dummy,HttpStatus.NOT_FOUND);
    }
    return ResponseEntity.ok(customer);
  }

  @PostMapping
  public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
    customerService.addCustomer(customer);
    return new ResponseEntity<>("Customer Added Successfully!",HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> updateCustomer(@PathVariable Long id,@RequestBody Customer customer) {
    boolean isUpdated = customerService.updateCustomer(id,customer);
    if(isUpdated) {
      return ResponseEntity.ok("Customer Updated Successfully!");
    }
    return new ResponseEntity<>("No Customer available with id : "+id, HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
    boolean isDeleted = customerService.deleteCustomer(id);
    if(isDeleted) {
      return ResponseEntity.ok("Customer Deleted Successfully!");
    }
    return new ResponseEntity<>("No Customer available with id : "+id, HttpStatus.NOT_FOUND);
  }
}
