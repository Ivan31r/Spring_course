package lessons.springLessons.repositories;

import lessons.springLessons.entities.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
@Data
@NoArgsConstructor
public class ProductRepository {
    private List<Product> productList;
    private Long id=0L;

    @PostConstruct
    public void init(){
    productList= new ArrayList<>();
    productList.add(new Product(++id,"Milk",80L));
    productList.add(new Product(++id,"Coffee",250L));
    }

    public List<Product> findAll(){
        return Collections.unmodifiableList(productList);
    }

    public Product saveOrUpdate(Product product){
        if (product.getId()==null){
            id++;
            product.setId(id);
            productList.add(product);
            return product;
        }else {
            for (int i=0;i<productList.size();i++){
                if (productList.get(i).getId().equals(product.getId())){
                    productList.set(i,product);
                    return product;
                }
            }
        }
        throw  new IllegalArgumentException("WTF");
    }

    public Product findById(Long id){
        for (Product product : productList){
            if  (product.getId().equals(id)){
                return product;
            }
        }
        throw  new IllegalArgumentException("Product not found =(");
    }

}
