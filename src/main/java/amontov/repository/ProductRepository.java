package amontov.repository;

import amontov.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;
@Component
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAll();
    @Query("select p from Product p where p.id = :id")
    Optional<Product> findById(@Param ("id") int id);
    @Query("select p from Product p where p.name = :name")
    Optional <Product> findByName(@Param("name") String name);
    @Query("select p from Product p order by id")
    List<Product> findByOrderByName ();

    @Query("select p from Product p where p.manufacture = :manufacture")
    Optional<Product> findByManufacture (@Param("manufacture") String manufacture);


}