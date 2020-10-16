package lessons.springLessons.services;

import lessons.springLessons.entities.Product;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
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


    public List<Product> findAll(Integer page) {
        if (page == null || page < 1) {
            page = 1;
        }
        return productRepository.findAll(PageRequest.of(page - 1, 5)).getContent();
    }

    public Page<Product> findAllBySpec(Specification<Product> specification, Integer page){
        if (page==null || page<1L){
            page=1;
        }
        return productRepository.findAll(specification,PageRequest.of(page,5));
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No matchers"));
    }

    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

    public List<Product> findAllByMinCost(Long cost) {
        return productRepository.findAllByCostLessThan(cost);
    }

}
