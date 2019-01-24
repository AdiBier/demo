package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "patients")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="first_name")
    @Size(min = 1, max = 50)
    private String firstName;
    @Column(name="last_name")
    @Size(min = 1, max = 50)
    private String lastName;

}
