package io.noobi.ecommerce.service;

import io.noobi.ecommerce.model.Product;

import java.util.List;

public interface ProductService {

  List<Product> getAllProduct();

  Product getProductById(Long id);

  void addProduct(Product product);

  boolean updateProduct(Long id,Product updateProduct);

  boolean deleteProduct(Long id);

}
