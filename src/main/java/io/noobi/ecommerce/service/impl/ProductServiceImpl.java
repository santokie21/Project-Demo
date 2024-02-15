package io.noobi.ecommerce.service.impl;

import io.noobi.ecommerce.model.Product;
import io.noobi.ecommerce.repository.ProductRepository;
import io.noobi.ecommerce.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

  private ProductRepository productRepository;

  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository=productRepository;
  }
  @Override
  public List<Product> getAllProduct(){
    return productRepository.findAll();
  }

  @Override
  public Product getProductById(Long id){
    return productRepository.findById(id).orElse(null);
  }

  @Override
  public void addProduct(Product product){
    productRepository.save(product);
  }

  @Override
  public boolean updateProduct(Long id, Product body){
    Product product = getProductById(id);
    if(product != null) {

      if(body.getName() != null) product.setName(body.getName());
      if(body.getTitle() != null) product.setTitle(body.getTitle());
      if(body.getBrand() != null) product.setBrand(body.getBrand());
      if(body.getDescription() != null) product.setDescription(body.getDescription());
      productRepository.save(product);

      return true;
    }
    return false;
  }

  @Override
  public boolean deleteProduct(Long id){
    Product product = getProductById(id);
    if( product != null ) {
      productRepository.deleteById(id);
      return true;
    }
    return false;
  }

}
