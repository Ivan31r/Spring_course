package lessons.springLessons.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Long id;
    private String title;
    private Long cost;


}
