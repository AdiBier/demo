package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "scheduled_treatments")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduledTreatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="treatment_id", nullable = false)
    private Treatment treatment;

    @Valid
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="visit_id", nullable = false)
    private Visit visit;

    @DecimalMin("0")
    @DecimalMax("1000000")
    private BigDecimal paid;


    @Size(min = 1, max = 20)
    private String status;
}
