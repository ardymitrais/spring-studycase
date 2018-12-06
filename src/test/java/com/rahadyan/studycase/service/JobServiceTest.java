package com.rahadyan.studycase.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.rahadyan.studycase.model.Job;
import com.rahadyan.studycase.repository.JobRepository;

public class JobServiceTest {

	@Mock
    private JobRepository mockJobRepository;
    @InjectMocks
    private JobService jobServiceUnderTest;
    
    private Job job;
    
    
	@Before
    public void setUp() {
        initMocks(this);
        
        job = Job.builder()
                .id(1)
                .company("PT Mitrais")
                .industry("Software House")
                .description("Test")
                .location("Yogyakarta")
                .salary(1000000)
                .title("Backend Java")
                .build();

        Mockito.when(mockJobRepository.save(any()))
                .thenReturn(job);
        Mockito.when(mockJobRepository.findById(any()).get())
                .thenReturn(job);
    }
	
}
