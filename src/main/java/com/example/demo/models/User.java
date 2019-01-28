package com.example.demo.models;

import com.example.demo.validators.adnotations.UniqueUsername;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Valid
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="patient_id", nullable = false)
    private Patient patient;

    @Size(min = 4, max = 36)
    @UniqueUsername
    private String username;

    private String password;

    @Transient//nie będzie odwzorowana w db
    private String passwordConfirm;
    private boolean enabled = false;
}
