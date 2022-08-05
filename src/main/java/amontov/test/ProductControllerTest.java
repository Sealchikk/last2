package amontov.test;

import amontov.config.Application;
import amontov.controllers.ProductController;
import amontov.models.Product;
import amontov.repository.ProductRepository;
import amontov.service.impl.ProductServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Application.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    private List<Product> mockDB() {
        List<Product> list = new ArrayList<>();
        list.add(createProduct());
        list.add(createProduct2());
        list.add(createProduct3());
        return list;
    }

    private final ProductController productController = new ProductController(new ProductServiceImpl(new ProductRepository() {
        @Override
        public List<Product> findAll() {
            return null;
        }

        @Override
        public Optional<Product> findById(int id) {
            List<Product> list = mockDB();
            for (Product p : list) {
                if (p.getId() == id) {
                    return Optional.of(p);
                }
            }
            return Optional.empty();
        }

        @Override
        public Optional<Product> findByName(String name) {
            return Optional.empty();
        }

        @Override
        public List<Product> findByOrderByName() {
            return null;
        }

        @Override
        public Optional<Product> findByManufacture(String manufacture) {
            return Optional.empty();
        }

        @Override
        public List<Product> findAll(Sort sort) {
            return null;
        }

        @Override
        public List<Product> findAllById(Iterable<Integer> integers) {
            return null;
        }

        @Override
        public <S extends Product> List<S> saveAll(Iterable<S> entities) {
            return null;
        }

        @Override
        public void flush() {

        }

        @Override
        public <S extends Product> S saveAndFlush(S entity) {
            return null;
        }

        @Override
        public <S extends Product> List<S> saveAllAndFlush(Iterable<S> entities) {
            return null;
        }

        @Override
        public void deleteAllInBatch(Iterable<Product> entities) {

        }

        @Override
        public void deleteAllByIdInBatch(Iterable<Integer> integers) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public Product getOne(Integer integer) {
            return null;
        }

        @Override
        public Product getById(Integer integer) {
            return null;
        }

        @Override
        public Product getReferenceById(Integer integer) {
            return null;
        }

        @Override
        public <S extends Product> List<S> findAll(Example<S> example) {
            return null;
        }

        @Override
        public <S extends Product> List<S> findAll(Example<S> example, Sort sort) {
            return null;
        }

        @Override
        public Page<Product> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public <S extends Product> S save(S entity) {
            return entity;
        }

        @Override
        public Optional<Product> findById(Integer integer) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(Integer integer) {
            return false;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Integer integer) {

        }

        @Override
        public void delete(Product entity) {

        }

        @Override
        public void deleteAllById(Iterable<? extends Integer> integers) {

        }

        @Override
        public void deleteAll(Iterable<? extends Product> entities) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public <S extends Product> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends Product> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends Product> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends Product> boolean exists(Example<S> example) {
            return false;
        }

        @Override
        public <S extends Product, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
            return null;
        }
    }));

    @Test
    public void addNewProductTest_thenStatus200() throws Exception {
        val answer = productController.addNewProduct(createProduct());
        assertEquals(HttpStatus.OK, answer.getStatusCode());
    }

    @Test
    public void productsAllTest_thenStatus200() throws Exception {
        val answer = productController.getAll();
        assertEquals(HttpStatus.OK, answer.getStatusCode());
    }

    @Test
    public void productsAllFilterTest_thenStatus200() throws Exception {
        val answer = productController.getFilter();
        assertEquals(HttpStatus.OK, answer.getStatusCode());
    }


    @Test
    public void getOneForIdTest_thenStatus200() throws Exception {
        val answer = productController.getOne(1);
        assertEquals(HttpStatus.OK, answer.getStatusCode());
    }

    @Test
    public void getOneForIdTest_thenStatus404() throws Exception {
        val answer = productController.getOne(4);
        assertEquals(HttpStatus.NOT_FOUND, answer.getStatusCode());
    }

    @Test
    public void changeProductTest_thenStatus200() throws Exception {
        val answer = productController.changeQuantityProduct(1,1);
        assertEquals(HttpStatus.OK, answer.getStatusCode());
    }

    @Test
    public void changeProductTest_thenStatus404() throws Exception {
        val answer = productController.changeQuantityProduct(4,1);
        assertEquals(HttpStatus.NOT_FOUND, answer.getStatusCode());
    }

    @Test
    public void toBuyProductTest_thenStatus200 () throws Exception {
        val answer = productController.toBuy(1,10);
        assertEquals(HttpStatus.OK, answer.getStatusCode());
    }

    @Test
    public void toBuyProductTest_thenStatus404 () throws Exception {
        val answer = productController.toBuy(4,10);
        assertEquals(HttpStatus.NOT_FOUND, answer.getStatusCode());
    }

    @Test
    public void deleteProduct_thenStatus200 () {
        val answer = productController.deleteProduct(1);
        assertEquals(HttpStatus.OK, answer.getStatusCode());
    }

    @Test
    public void deleteProduct_thenStatus404 () {
        val answer = productController.deleteProduct(4);
        assertEquals(HttpStatus.NOT_FOUND, answer.getStatusCode());
    }


    private Product createProduct() {
        Product product = new Product();
        product.setId(1);
        product.setName("Hleb");
        product.setCategory("Hlebobulochnie");
        product.setPrice(18);
        product.setQuantity(12);
        product.setManufacture("Domasnii");
        product.setWeight("Shtuk");
        return product;
    }

    private Product createProduct2() {
        Product product = new Product();
        product.setId(1);
        product.setName("Hleb");
        product.setCategory("Hlebobulochnie");
        product.setPrice(12);
        product.setQuantity(12);
        product.setManufacture("Domasnii");
        product.setWeight("Shtuk");
        return product;
    }

    private Product createProduct3() {
        Product product = new Product();
        product.setId(1);
        product.setName("Hleb");
        product.setCategory("Hlebobulochnie");
        product.setPrice(12);
        product.setQuantity(12);
        product.setManufacture("Domasnii");
        product.setWeight("Shtuk");
        return product;
    }
}
