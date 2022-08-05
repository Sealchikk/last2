package amontov.service;

import amontov.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    List<Product> showAll();

    Optional<Product> getOne(int id);

    Product addNewProduct(Product product);

    String changeQuantityProduct(int id, int quantity);

    String toBuyProduct(int id, int newQuantity);

    String deleteProduct(int id);

    List<Product> getFilter();

    void deleteAll();
}
