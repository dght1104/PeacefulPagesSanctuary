package peaceful.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @Column(name = "orders_id")
    private String id;

    @Column(name = "orders_date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "cus_id")
    private Customer customer;

    @Column(name = "orders_status")
    private String status;

    @Column(name = "orders_total")
    private BigDecimal total;

    @Column(name = "shipping_fee")
    private BigDecimal shippingFee;

    @ManyToOne
    @JoinColumn(name = "coupon_code")
    private Coupon coupon;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;
}
