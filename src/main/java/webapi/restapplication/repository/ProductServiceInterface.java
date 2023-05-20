package webapi.restapplication.repository;

import webapi.restapplication.model.Product;

import java.util.List;

public interface ProductServiceInterface {

    List<Product> getAllProducts();

    Product getProductById(Long id);

    List<Product> getProductsByCategory(String category);

    Product createProduct(Product newProduct);

    Product updateProduct(Long id, Product newProduct);

    void deleteProduct(Long id);

}
