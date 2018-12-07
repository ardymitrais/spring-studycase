package com.rahadyan.studycase.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.rahadyan.studycase.model.Job;
import com.rahadyan.studycase.model.Role;
import com.rahadyan.studycase.model.User;

@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryTest {
	@Mock
	private UserRepository mockUserRepository;

	private User user;

	@Test
	public void testFindByEmail() {
		user = User.builder().id(1).firstName("Gustavo").lastName("Ponce").email("test@test.com").address("Jogja")
				.education("Information Engineering").school("UGM").password("password").build();
		Mockito.when(mockUserRepository.findByEmail(anyString())).thenReturn(user);

		// Action
		User user = mockUserRepository.findByEmail("test@test.com");

		// Assertion
		assertEquals("Information Engineering", user.getEducation());

		// Verification
		Mockito.verify(mockUserRepository, atLeastOnce()).findByEmail(Mockito.anyString());
	}

	@Test
	public void testFindAllUserByRoleId() {
		Mockito.when(mockUserRepository.findAllUserByRoleId(Mockito.anyInt()))
				.thenReturn(Arrays.asList(
						new User(1, "email1@email.com", "password1", "Suko", "Tyas", "IT", "UGM", "Ponorogo", 2),
						new User(2, "email2@email.com", "password2", "Nur", "Lita", "IT", "UGM", "Sragen", 2)));

		// Action
		List<User> items = mockUserRepository.findAllUserByRoleId(2);
		// Assertion
		assertEquals(2, items.size());
		assertEquals("Ponorogo", items.get(0).getAddress());
		// Verification
		Mockito.verify(mockUserRepository, atLeastOnce()).findAllUserByRoleId(Mockito.anyInt());
	}

}
