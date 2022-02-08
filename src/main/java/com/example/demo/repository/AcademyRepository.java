package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Academy;

public interface AcademyRepository extends JpaRepository<Academy, Long> {

}
