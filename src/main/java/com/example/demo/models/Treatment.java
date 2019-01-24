package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "treatments")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DecimalMin("0")
    @DecimalMax("1000000")
    private BigDecimal price;

    @Size(min = 1, max = 36)
    private String name;

    private boolean active;

}
