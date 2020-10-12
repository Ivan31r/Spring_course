package lessons.springLessons.services;

import lessons.springLessons.entities.Product;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lessons.springLessons.repositories.ProductRepository;
import java.util.List;

@Service
@NoArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveOrUpdate(Product product){
        return productRepository.saveOrUpdate(product);
    }

}
