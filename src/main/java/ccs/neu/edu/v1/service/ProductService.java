package ccs.neu.edu.v1.service;

import ccs.neu.edu.v1.dao.ProductDao;
import ccs.neu.edu.v1.model.Product;
import com.google.inject.Inject;

import java.util.List;

public class ProductService {

  private final ProductDao productDao;

  @Inject
  public ProductService(ProductDao productDao) {
    this.productDao = productDao;
  }

  public List<Product> getAllProducts(){
    return productDao.getAllProducts();
  }
}
