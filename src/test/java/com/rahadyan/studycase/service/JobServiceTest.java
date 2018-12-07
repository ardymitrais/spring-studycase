package com.rahadyan.studycase.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.rahadyan.studycase.model.Job;
import com.rahadyan.studycase.repository.JobRepository;

@RunWith(MockitoJUnitRunner.class)
public class JobServiceTest {

	@Mock
	private JobRepository mockJobRepository;
	@InjectMocks
	private JobService jobServiceUnderTest;

	private Job job;

	@Before
    public void setUp() {
        initMocks(this);
        
        job = Job.builder().id(1).company("PT Mitrais").title("Beckend Dev").description("Urgent").location("Jakarta")
				.industry("Software House").build();

        
    }
	
	@Test
	public void testFindAll() {
		// Preparation
		Mockito.when(mockJobRepository.findAll())
		.thenReturn(Arrays.asList(
				new Job(1, "Backend Dev", "PT Mitrais", "Urgent", "Jakarta", "Software House", 10000000,
						Job.Type.FULLTIME),
				new Job(2, "Frontend Dev", "PT Mitrais", "Urgent", "Bali", "Software House", 10000000,
						Job.Type.FULLTIME)));

		// Action
		List<Job> items = jobServiceUnderTest.findAll();

		// Assertion
		assertEquals(2, items.size());
		assertEquals(Job.Type.FULLTIME, items.get(0).getType());
		// Verification
		Mockito.verify(mockJobRepository, atLeastOnce()).findAll();
	}
	
	@Test
	public void testFindById() {
		// Preparation 
		Mockito.when(mockJobRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(job));

		// Action
		Job result = jobServiceUnderTest.findById(1);

		// Assertion
		assertEquals("PT Mitrais", result.getCompany());
		// Verification
		Mockito.verify(mockJobRepository, atLeastOnce()).findById(Mockito.anyInt());
	}

	@Test
	public void testSave() {
		// Preparation
		Mockito.when(mockJobRepository.save(any())).thenReturn(job);

		// Action
		String result = jobServiceUnderTest.save(Job.builder().build());

		// Assertion
		assertEquals("success", result);
		// Verification
		Mockito.verify(mockJobRepository, atLeastOnce()).save(any());
	}
	
	@Test
	public void testDelete() {
		// Preparation
		Mockito.when(mockJobRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(job)).thenReturn(null);

		// Action
		String result = jobServiceUnderTest.delete(1);

		// Assertion
		assertEquals("success", result);
		// Verification
		Mockito.verify(mockJobRepository, atLeastOnce()).findById(Mockito.anyInt());
	}
}
