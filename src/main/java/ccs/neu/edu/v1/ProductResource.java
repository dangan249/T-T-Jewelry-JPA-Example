package ccs.neu.edu.v1;

import ccs.neu.edu.v1.model.Product;
import com.google.inject.Inject;
import ccs.neu.edu.v1.service.ProductService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

  private final ProductService productService;
  @Inject
  public ProductResource(ProductService productService) {
    this.productService = productService;
  }

  @GET
  public List<Product> getAllProducts() {
    return productService.getAllProducts();
  }

}