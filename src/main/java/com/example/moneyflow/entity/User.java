package com.example.moneyflow.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<Income> incomes = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<Expense> expenses = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<Category> categories = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
