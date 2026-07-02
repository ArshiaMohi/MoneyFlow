package com.example.moneyflow.entity;

import com.example.moneyflow.entity.enums.CategoryType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private CategoryType type;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "category")
    @Builder.Default
    private List<Income> incomes = new ArrayList<>();
    @OneToMany(mappedBy = "category")
    @Builder.Default
    private List<Expense> expenses = new ArrayList<>();
}
