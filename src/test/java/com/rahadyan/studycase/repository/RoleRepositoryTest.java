package com.rahadyan.studycase.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.rahadyan.studycase.model.Role;

@RunWith(MockitoJUnitRunner.class)
public class RoleRepositoryTest {
	@Mock
	private RoleRepository mockRoleRepository;

	private Role role;

	@Before
	public void setUp() {
		initMocks(this);

		role = Role.builder().id(1).role("FULLTIME").build();

	}

	@Test
	public void testFindByRole() {
		// Preparation
		Mockito.when(mockRoleRepository.findByRole(Mockito.anyString())).thenReturn(role);

		// Action
		Role role = mockRoleRepository.findByRole("FULLTIME");

		// Assertion
		assertEquals("FULLTIME", role.getRole());
		
		// Verification
		Mockito.verify(mockRoleRepository, atLeastOnce()).findByRole(Mockito.anyString());
	}

}
