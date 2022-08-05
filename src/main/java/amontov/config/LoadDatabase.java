package amontov.Config;

import amontov.models.Product;
import amontov.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("amontov")
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ProductService productService) {
        return args -> {
            log.info(productService.getFilter().toString());
        };

    }

    private Product createProduct() {
        Product product = new Product();
        product.setName("Hle");
        product.setCategory("Hlebobulochnie");
        product.setPrice(12);
        product.setQuantity(12);
        product.setManufacture("Domasni");
        product.setWeight("Shtuk");
        return product;
    }

}