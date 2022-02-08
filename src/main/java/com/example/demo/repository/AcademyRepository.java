package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Academy;

public interface AcademyRepository extends JpaRepository<Academy, Long> {

	@Query("select a from Academy a join fetch a.subjects")
	List<Academy> findAllJoinFetch();
	
	
	@EntityGraph(attributePaths = "subjects")
	@Query("select a from Academy a")
	List<Academy> findAllEntityGraph();
	
	
	@Query("select DISTINCT a from Academy a join fetch a.subjects")
	List<Academy> findAllJoinFetchDistinct();
	
	
	@EntityGraph(attributePaths = "subjects")
	@Query("select DISTINCT a from Academy a")
	List<Academy> findAllEntityGraphDistinct();
	
	
	@Query("select DISTINCT a from Academy a join fetch a.subjects s join fetch s.teacher")
	List<Academy> findAllJoinFetchWithTeacher();
	
	
	@EntityGraph(attributePaths = {"subjects", "teacher"})
	@Query("select DISTINCT a from Academy a")
	List<Academy> findAllEntityGraphWithTeacher();
	
}
