package com.example.demo.dto.movie;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieResponse {
    private Long id;

    private String name;
    private String prize;
    private String description;
    private String created_date;
    private Integer age_access;
    private String Type;
}
