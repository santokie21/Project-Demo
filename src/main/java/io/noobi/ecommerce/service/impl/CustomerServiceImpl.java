package io.noobi.ecommerce.service.impl;

import io.noobi.ecommerce.model.Customer;
import io.noobi.ecommerce.repository.CustomerRepository;
import io.noobi.ecommerce.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

  public CustomerServiceImpl(CustomerRepository customerRepository) {
    this.customerRepository=customerRepository;
  }
  @Override
  public List<Customer> getAllCustomer(){
    return customerRepository.findAll();
  }

  @Override
  public Customer getCustomerById(Long id){
    return customerRepository.findById(id).orElse(null);
  }

  @Override
  public void addCustomer(Customer customer){
    customerRepository.save(customer);
  }

  @Override
  public boolean updateCustomer(Long id,Customer body){
    Customer customer = getCustomerById(id);
    if(customer != null) {

      if(body.getFirstName() != null) customer.setFirstName(body.getFirstName());
      if(body.getLastName() != null) customer.setLastName(body.getLastName());
      if(body.getMobile() != null) customer.setMobile(body.getMobile());
      if(body.getEmail() != null) customer.setEmail(body.getEmail());
      customerRepository.save(customer);

      return true;
    }
    return false;
  }

  @Override
  public boolean deleteCustomer(Long id) {

    if(customerRepository.findById(id).isPresent()) {
      customerRepository.deleteById(id);
      return true;
    }
    return false;
  }
}
