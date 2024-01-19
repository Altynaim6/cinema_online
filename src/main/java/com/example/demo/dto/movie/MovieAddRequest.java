package com.example.demo.dto.movie;

import com.example.demo.entities.Type;
import jakarta.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieAddRequest {
    private String movie_name;
    private String description;
    private String type;
    private Integer age_access;
    private Integer prize;


}
