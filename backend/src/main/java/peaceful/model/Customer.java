package peaceful.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cus_id")
    private Integer id;

    @Column(name = "cus_name")
    private String name;

    @Column(name = "cus_email", unique = true)
    private String email;

    @Column(name = "cus_phone")
    private String phone;

    @Column(name = "cus_username", unique = true)
    private String username;

    @Column(name = "cus_password")
    private String password;

    @Column(name = "cus_group")
    private String customerGroup;

    @Column(name = "is_verified")
    private Boolean verified;

    @Column(name = "is_active")
    private Boolean active;

    @Column(name = "total_spent")
    private BigDecimal totalSpent;
}
