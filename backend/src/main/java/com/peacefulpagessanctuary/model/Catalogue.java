package com.peacefulpagessanctuary.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "catalogue")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Catalogue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id")
    private Long id;

    @Column(name = "cat_name")
    private String name;
}