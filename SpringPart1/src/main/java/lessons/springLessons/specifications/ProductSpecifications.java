package lessons.springLessons.specifications;

import lessons.springLessons.entities.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {
    public static Specification<Product> costGreaterOrEqualsThan(int minCost){
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("cost"),minCost);
    }
}
