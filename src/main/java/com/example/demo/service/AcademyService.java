package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Academy;
import com.example.demo.repository.AcademyRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AcademyService {

	@Autowired
	private AcademyRepository academyRepository;
	
	@Transactional(readOnly = true)
	public List<String> findAllSubjectNames(){
		log.info(">>>>>>>>>>[Starting AcademyRepository::findAllSubjectNames]<<<<<<<<<<");
		return extractSubjectNames(academyRepository.findAllJoinFetch());	
	}
	
	private List<String> extractSubjectNames(List<Academy> academies){
		log.info(">>>>>>>>>>[모든 과목을 추출한다.]<<<<<<<<<<");
		log.info("Academy Size : {} ", academies.size());
		
		return academies.stream()
						.map(a -> a.getSubjects().get(0).getName())
						.collect(Collectors.toList());
	}
}
