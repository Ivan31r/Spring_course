package lessons.springLessons.controllers;

import lessons.springLessons.entities.Product;
import lessons.springLessons.services.ProductService;
import lessons.springLessons.specifications.ProductSpecifications;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@NoArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    @RequestMapping("all_with_spec")
    public String showAllProductBySpec(Model model,
                                       @RequestParam(name = "p",required = false) Integer page,
                                       @RequestParam(name = "cost",required = false) Integer minCost){
        Specification<Product> specification = Specification.where(null);
        if (minCost!=null){
            specification=specification.and(ProductSpecifications.costGreaterOrEqualsThan(minCost));
        }
        List<Product> products = productService.findAllBySpec(specification,page).getContent();
        model.addAttribute("products",products);
        return "all_products";
    }

    @GetMapping
    @RequestMapping("/all")
    public String showAllProducts(@RequestParam (name = "p", required = false) Integer page, Model model) {
        List<Product> products = productService.findAll(page);
        model.addAttribute("products", products);
        return "all_products";
    }


    //localhost:8080/app/products/add
    @GetMapping("/add")
    public String showAddForm() {
        return "add_product_form";
    }

    @GetMapping("/")
    public String homePage(){
        return "home_page";
    }

    //localhost:8080/app/products/add (post)
    @PostMapping("/add")
    public String saveNewStudent(@ModelAttribute Product newProduct) {
        productService.saveOrUpdate(newProduct);
        return "redirect:/products/all";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "edit_product_form";
    }

    @PostMapping("/edit")
    public String modifyStudent(@ModelAttribute Product product) {
        productService.saveOrUpdate(product);
        return "redirect:/products/all";
    }

    @GetMapping("/json/{id}")
    @ResponseBody
    public Product showJsonProduct(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/find_by_min_cost")
    @ResponseBody
    public List<Product> findAllByMinCost(@RequestParam Long cost){
        return productService.findAllByMinCost(cost);
    }


}
