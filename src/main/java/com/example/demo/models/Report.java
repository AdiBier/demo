package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Size;

@Entity
@Table(name = "reports")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title")
    @Size(min = 1, max = 50)
    private String title;

    @Lob
    @Column(name="description", nullable = false)
    @Type(type = "text")
    private String description;

    @Valid
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="patient_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
}
