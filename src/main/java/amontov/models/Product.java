package amontov.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "name can't be blank")
    private String name;
    @NotBlank(message = "category can't be blank")
    private String category;
    @NotBlank(message = "price can't be blank")
    @Min(value = 0, message = "quantity can't be < 0")
    private double price;
    @NotBlank(message = "price can't be blank")
    @Min(value = 0, message = "quantity can't be < 0")
    private int quantity;
    @NotBlank(message = "weight can't be blank")
    private String weight;
    @NotBlank(message = "manufacture can't be blank")
    private String manufacture;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return id != null && Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}