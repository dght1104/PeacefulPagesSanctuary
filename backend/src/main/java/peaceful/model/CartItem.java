package peaceful.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CartItems",
       uniqueConstraints = @UniqueConstraint(columnNames = {"cus_id", "prod_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartitem_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cus_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "prod_id")
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;
}
