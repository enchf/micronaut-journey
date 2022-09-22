package com.gjug.micronaut.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Purchase implements Serializable {
    private static final long serialVersionUID = 987652L;

    private Long productId;
    private Integer quantity;
    private BigDecimal costPerUnit;
    private Boolean income;
}
