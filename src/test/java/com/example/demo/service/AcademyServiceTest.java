package com.example.demo.service;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.AfterTestMethod;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import com.example.demo.model.Academy;
import com.example.demo.model.Subject;
import com.example.demo.repository.AcademyRepository;


@SpringBootTest
public class AcademyServiceTest {

	@Autowired
	private AcademyRepository academyRepository;
	
	@Autowired
	private AcademyService academyService;
	
	@After
	public void cleanAll() {
		//academyRepository.deleteAll();
	}
	
	@Before
	public void setup() {
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

	
	@SuppressWarnings("deprecation")
	@Test
	public void AcademySubjectN1() throws Exception {
        
//		List<Academy> academies = new ArrayList<>();
//		
//		for(int i=0;i<10;i++) {
//			Academy academy = Academy.builder()
//									 .name("강남스쿨"+i)
//									 .build();
//			
//			academy.addSubject(Subject.builder()
//									  .name("자바웹개발"+i)
//									  .build());
//			academies.add(academy);
//		}
//		
//		academyRepository.saveAll(academies);
		
		
		
		List<String> subjectNames = academyService.findAllSubjectNames();
		
		assertThat(subjectNames.size(), is(10));
	}
	
}
