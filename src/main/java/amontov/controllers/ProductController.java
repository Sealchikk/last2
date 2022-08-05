package amontov.controllers;

import amontov.models.Product;
import amontov.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

import static java.util.Optional.ofNullable;
import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/product")
@Validated
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAll() {
        var list = productService.showAll();
        return ok(list);
    }

    @GetMapping("/all/filter")
    public ResponseEntity<List<Product>> getFilter() {
        var list = productService.getFilter();
        return ok(list);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getOne(@PathVariable("id")  int id) {
        var product = productService.getOne(id);
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> notFound().build());
    }

    @PostMapping("/addNew")
    public ResponseEntity<Product> addNewProduct(@Valid @RequestBody Product newProduct) {
        return ofNullable(productService.addNewProduct(newProduct))
                .map(ResponseEntity::ok)
                .orElse(notFound().build());
    }

    @PutMapping("/change/{id}/{quantity}")
    public ResponseEntity<String> changeQuantityProduct(@PathVariable("id") int id,
                                                        @PathVariable("quantity") @Min(1) int quantity) {
        return ofNullable(productService.changeQuantityProduct(id, quantity))
                .map(ResponseEntity::ok)
                .orElse(notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") int id) {
        return ofNullable(productService.deleteProduct(id))
                .map(ResponseEntity::ok)
                .orElse(notFound().build());
    }

    @PutMapping("/buy/{id}/{quantity}")
    public ResponseEntity<String> toBuy(@PathVariable("id") int id,
                                        @PathVariable("quantity") @Min(1) int quantity) {
        return ofNullable(productService.toBuyProduct(id, quantity))
                .map(ResponseEntity::ok)
                .orElse(notFound().build());

    }


}