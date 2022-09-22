package com.gjug.micronaut.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "types", schema = "catalogschema")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    private Set<Product> products = new HashSet<>();
}
