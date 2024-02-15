package io.noobi.ecommerce.service;

import io.noobi.ecommerce.model.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {

  List<Customer> getAllCustomer();

  Customer getCustomerById(Long id);

  void addCustomer(Customer customer);

  boolean updateCustomer(Long id,Customer customer);

  boolean deleteCustomer(Long id);
}
