package io.noobi.ecommerce.controller;

import io.noobi.ecommerce.model.Product;
import io.noobi.ecommerce.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService=productService;
  }

  @GetMapping
  public ResponseEntity<List<Product>> getAllProducts() {
      List<Product> products = productService.getAllProduct();
      if(products.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return ResponseEntity.ok(products);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> productById(@PathVariable Long id) {
    Product product = productService.getProductById(id);
    if(product == null) {
      Product dummy = new Product(0L,"Nothing","Nothing","No Product","Product Not Available with specific Id");
      return new ResponseEntity<>(dummy,HttpStatus.NOT_FOUND);
    }
    return ResponseEntity.ok(product);
  }

  @PostMapping
  public ResponseEntity<String> addProduct(@RequestBody Product product) {
    productService.addProduct(product);
    return new ResponseEntity<>("Product Created Successfully!",HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> updateProduct(@PathVariable long id,@RequestBody Product product) {
    boolean isUpdated = productService.updateProduct(id,product);
    if(isUpdated) {
      return ResponseEntity.ok("Product Updated Successfully!");
    }
    return new ResponseEntity<>("No Product available with id : " + id, HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
    boolean isDeleted = productService.deleteProduct(id);
    if(isDeleted) {
      return ResponseEntity.ok("Product Deleted Successfully!");
    }
    return new ResponseEntity<>("No Product available with id : " + id, HttpStatus.NOT_FOUND);
  }
}
