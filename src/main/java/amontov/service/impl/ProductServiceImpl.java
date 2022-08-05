package amontov.service.impl;

import amontov.error.Error;
import amontov.models.Product;
import amontov.repository.ProductRepository;
import amontov.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;

    @Override
    public List<Product> showAll() {
        return repository.findAll();
    }

    @Override
    public List<Product> getFilter() {
        return repository.findByOrderByName();
    }

    @Override
    public Optional<Product> getOne(int id) {
        return repository.findById(id);
    }

    @Override
    public Product addNewProduct(Product newProduct) {
        if (repository.findByName(newProduct.getName()).isPresent() &&
                repository.findByManufacture(newProduct.getManufacture()).isPresent()) {
            return null;
        } else {
            return repository.save(newProduct);
        }
    }

    @Override
    public String changeQuantityProduct(int id, int quantity) {
        if (checkProduct(id)) {
            Product product = repository.findById(id).get();
            product.setQuantity(product.getQuantity() + quantity);
            return ("the product was added in quantity " + quantity);
        } else {
            return null;
        }
    }

    @Override
    public String toBuyProduct(int id, int newQuantity) {
        if (checkProduct(id)) {
            Product product = repository.findById(id).get();
            if (product.getQuantity() - newQuantity > 0) {
                product.setQuantity(product.getQuantity() - newQuantity);
                repository.save(product);
                return ("the product was purchased in quantity " + newQuantity);
            } else {
                return new Error().errorToBuy();
            }
        } else {
            return null;
        }

    }

    @Override
    public String deleteProduct(int id) {
        if (checkProduct(id)) {
            Product product = repository.findById(id).get();
            if (product.getQuantity() == 0) {
                repository.delete(product);
                return ("the product has been removed");
            } else {
                return new Error().errorDelete();
            }
        }
        return null;
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    private boolean checkProduct(int id) {
        return repository.findById(id).isPresent();
    }
}
