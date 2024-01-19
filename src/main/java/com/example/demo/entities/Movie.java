package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String created_date;
    private Integer prize;
    private Integer age_access;

    @ManyToOne()
    private Type type;

    @ManyToOne()
    private Customer customer;


}
