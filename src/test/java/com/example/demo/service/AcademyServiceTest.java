package com.example.demo.service;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Academy;
import com.example.demo.model.Subject;
import com.example.demo.repository.AcademyRepository;

import lombok.extern.slf4j.Slf4j;


@SpringBootTest
@Slf4j
public class AcademyServiceTest {

	@Autowired
	private AcademyRepository academyRepository;
	
	@Autowired
	private AcademyService academyService;
	
	@AfterEach
	public void cleanAll() {
		log.info(">>>>>>>>>>[Starting AcademyServiceTest::cleanAll]<<<<<<<<<<");
		academyRepository.deleteAll();
	}
	
	@BeforeEach
	public void setup() {
		log.info(">>>>>>>>>>[Starting AcademyServiceTest::setup]<<<<<<<<<<");
		List<Academy> academies = new ArrayList<>();
		
		for(int i=0;i<10;i++) {
			Academy academy = Academy.builder()
									 .name("강남스쿨"+i)
									 .build();
			
			academy.addSubject(Subject.builder()
									  .name("자바웹개발"+i)
									  .build());
			academies.add(academy);
		}
		
		academyRepository.saveAll(academies);
		
	}

	
	@Test
	public void AcademySubjectN1() throws Exception {
		
		List<String> subjectNames = academyService.findAllSubjectNames();
		
		assertThat(subjectNames.size(), is(10));
	}
	
}
