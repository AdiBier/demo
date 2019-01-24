package com.example.demo.models;

import com.example.demo.controllers.components.CalculatorComponent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "visits")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="created_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdDate;

    @Column(name="modified_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date modifiedDate;

    @Column(name="visit_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date visitDate;

    @Valid
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="patient_id", nullable = false)
    private Patient patient;

    @Valid
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="dentist_id", nullable = false)
    private User dentist;

    @Valid
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "visit")
    private Set<ScheduledTreatment> scheduledTreatments;

    public BigDecimal getPrice(){
        BigDecimal result = new BigDecimal(0);
        for(ScheduledTreatment scheduledTreatment:scheduledTreatments) {
            result = CalculatorComponent.Add(result,scheduledTreatment.getTreatment().getPrice());
        }
        return result;
    }

    public BigDecimal getPaid(){
        BigDecimal result = new BigDecimal(0);
        for(ScheduledTreatment scheduledTreatment:scheduledTreatments) {
            result = CalculatorComponent.Add(result,scheduledTreatment.getPaid());
        }
        return result;
    }
}

