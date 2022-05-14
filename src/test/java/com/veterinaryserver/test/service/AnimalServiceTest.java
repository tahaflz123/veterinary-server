package com.veterinaryserver.test.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import com.veterinaryserver.entity.animal.Animal;
import com.veterinaryserver.entity.animal.AnimalType;
import com.veterinaryserver.entity.animal.Gender;
import com.veterinaryserver.entity.user.User;
import com.veterinaryserver.model.request.AnimalCreationRequest;
import com.veterinaryserver.model.request.AnimalUpdateRequest;
import com.veterinaryserver.repository.AnimalRepository;
import com.veterinaryserver.service.AnimalService;
import com.veterinaryserver.service.ErrorMessagesService;
import com.veterinaryserver.service.UserService;
import com.veterinaryserver.test.helper.AnimalHelper;
import com.veterinaryserver.test.helper.UserHelper;

@ExtendWith(MockitoExtension.class)
public class AnimalServiceTest {

	private final Logger logger = LoggerFactory.getLogger(AnimalServiceTest.class);
	
	@InjectMocks
	private AnimalService animalService;
	
	@Mock
	private UserService userService;
	
	@Mock
	private AnimalRepository animalRepository;
	
	@Mock
	private ErrorMessagesService errorMessagesService;
	
	
	@Test
	void given_animal_creation_request_when_parse_animal_creation_request_then_it_should_return_parsed_animal() {
		AnimalCreationRequest request = AnimalHelper.createAnimalCreationRequest();
		User user = UserHelper.createUser();

		Animal animal = this.animalService.parseAnimalCreationRequestToAnimal(request, user);

		logger.info("Parsed animal, {}", animal.toString());
		
		assertNotNull(animal);
		assertEquals(request.getAge(), animal.getAge());
		assertTrue(request.getName().equals(animal.getName()));
		assertTrue(request.getAbout().equals(animal.getAbout()));
		assertTrue(request.getBreed().equals(animal.getBreed()));
		assertTrue(request.getGender().equals(animal.getGender()));
		assertTrue(request.getType().equals(animal.getType()));
		assertEquals(user, animal.getUser());
	}
	
	@Test
	void given_animal_update_request_when_parse_animal_update_request_then_it_should_return_parsed_animal() {
		Animal animal = AnimalHelper.createAnimal();
		AnimalUpdateRequest request = AnimalHelper.createAnimalUpdateRequest();
		
		Animal updated = this.animalService.parseAnimalUpdateRequest(animal, request);
		
		logger.info("Updated Animal, {}", updated.toString());
		
		assertNotNull(updated);
		assertEquals(request.getAge(), updated.getAge());
		assertTrue(request.getName().equals(updated.getName()));
		assertTrue(request.getAbout().equals(updated.getAbout()));
		assertTrue(request.getBreed().equals(updated.getBreed()));
		assertTrue(Gender.valueOf(request.getGender()).equals(updated.getGender()));
		assertTrue(AnimalType.valueOf(request.getType()).equals(updated.getType()));
		assertEquals(updated.getUser(),animal.getUser());
	}
	
	@Test
	void given_animal_when_find_by_id_then_it_should_return_created_animal() {
		Animal animal = AnimalHelper.createAnimal();
		
		when(this.animalRepository.findById(1L)).thenReturn(Optional.of(animal));
		
		Animal response = this.animalService.findById(1L);
		
		logger.info("Given animal, {}", animal.toString());
		logger.info("Found animal, {}", response.toString());
		
		assertNotNull(animal);
		assertNotNull(response);
		assertEquals(animal.getAge(), response.getAge());
		assertTrue(animal.getName().equals(response.getName()));
		assertTrue(animal.getAbout().equals(response.getAbout()));
		assertTrue(animal.getBreed().equals(response.getBreed()));
		assertTrue(animal.getGender().equals(response.getGender()));
		assertTrue(animal.getType().equals(response.getType()));
		assertEquals(animal.getUser(),response.getUser());
	}
	
	/**
	 * 
	 * public Animal createAnimal(AnimalCreationRequest request) {
		User user = userService.getLoggedInUser();
		Animal animal = this.parseAnimalCreationRequestToAnimal(request, user);
		Animal saved = this.animalRepository.save(animal);
		return saved;
	}
	 * 
	 */
	
	/*@Test
	void given_animal_creation_request_when_create_animal_then_it_should_return_created_animal() {
		User loggedIn = UserHelper.createUser();
		AnimalCreationRequest request = AnimalHelper.createAnimalCreationRequest();
		
		when(this.userService.getLoggedInUser()).thenReturn(loggedIn);
		
		when(this.animalService.parseAnimalCreationRequestToAnimal(request, loggedIn)).thenReturn(this.animalService.parseAnimalCreationRequestToAnimal(request, loggedIn));
		
		when(this.animalRepository.save(any(Animal.class))).thenReturn(any(Animal.class));
		
		Animal created = this.animalService.createAnimal(request);
		
		logger.info("Created animal, {}", created.toString());
		
		/*assertNotNull(animal);
		assertNotNull(response);
		assertEquals(animal.getAge(), response.getAge());
		assertTrue(animal.getName().equals(response.getName()));
		assertTrue(animal.getAbout().equals(response.getAbout()));
		assertTrue(animal.getBreed().equals(response.getBreed()));
		assertTrue(animal.getGender().equals(response.getGender()));
		assertTrue(animal.getType().equals(response.getType()));
		assertEquals(animal.getUser(),response.getUser());
		
	}*/


	/*@Test
	void given_animal_id_when_delete_animal_with_id_then_verify() {
		Long id = 100L;
		User user = UserHelper.createAdminUser();
		
		when(this.userService.getLoggedInUser()).thenReturn(user);
		doNothing().when(this.animalService).deleteById(id);
		this.animalService.deleteById(id);
		verify(this.animalRepository, times(1)).deleteById(id);
	}

	
	/*@Test
	void given_animal_id_when_delete_animal_with_non_admin_user_then_verify_false() {
		Long id = 100L;
		User user = UserHelper.createUser();
		
		when(this.userService.getLoggedInUser()).thenReturn(user);
		this.animalService.deleteById(id);
		verify(animalService).forbiddenException();
		
	}*/






	
	/*@Test
	void given_animal_and_user_when_check_is_sender_user_of_animal_then_it_should_return_true() {
		Animal animal = AnimalHelper.createAnimal();
		User user = UserHelper.createUser();
		
		boolean senderCheck = this.animalService.isSender(user, animal);
		
		logger.info("Sender check, {}", senderCheck);
		
		assertNotNull(senderCheck);
		assertTrue(senderCheck);
	}
	
	@Test
	void given_animal_and_user_when_check_is_sender_user_of_animal_then_it_should_return_false() {
		Animal animal = AnimalHelper.createAnimal();
		User user = UserHelper.createUser();
		user.setUsername("asdfasdfasdf");
		
		boolean senderCheck = this.animalService.isSender(user, animal);
		
		logger.info("Sender check, {}", senderCheck);
		
		assertNotNull(senderCheck);
		assertFalse(senderCheck);
	}
	*/
	
	
	
	
}
