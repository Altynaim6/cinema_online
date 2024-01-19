package com.example.demo.repositories;

import com.example.demo.entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeRepository extends JpaRepository<Type, Long> {

    Optional<Object> findByName(String name);
}
