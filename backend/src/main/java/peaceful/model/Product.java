package peaceful.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_id")
    private Integer id;

    @Column(name = "prod_name")
    private String name;

    @Column(name = "prod_received")
    private Integer received;

    @Column(name = "prod_sold")
    private Integer sold;

    @Column(name = "prod_price")
    private BigDecimal price;

    @Column(name = "prod_discount")
    private BigDecimal discount;

    @Column(name = "prod_description")
    private String description;
}
