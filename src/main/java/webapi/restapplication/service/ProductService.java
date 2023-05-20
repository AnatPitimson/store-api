package webapi.restapplication.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapi.restapplication.exception.ProductNotFoundException;
import webapi.restapplication.model.Product;
import webapi.restapplication.repository.ProductRepo;
import webapi.restapplication.repository.ProductServiceInterface;

import java.util.List;

@Service
public class ProductService implements ProductServiceInterface {

    private ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepo.getProductsByCategory(category);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product existingProduct = productRepo.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        if (existingProduct != null) {
            existingProduct.setProductName(product.getProductName());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setPrice(product.getPrice());
            //existingProduct.setCreationDate(LocalDateTime.now());
            return productRepo.save(existingProduct);
        }
        return createProduct(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepo.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        productRepo.delete(product);
    }


}
