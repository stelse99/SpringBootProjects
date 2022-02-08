package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Academy;

public interface AcademyRepository extends JpaRepository<Academy, Long> {

	@Query("select a from Academy a join fetch a.subjects")
	List<Academy> findAllJoinFetch();
}
