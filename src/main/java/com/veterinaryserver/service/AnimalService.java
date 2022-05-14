package com.veterinaryserver.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.veterinaryserver.entity.animal.Animal;
import com.veterinaryserver.entity.animal.AnimalType;
import com.veterinaryserver.entity.animal.Gender;
import com.veterinaryserver.entity.user.User;
import com.veterinaryserver.entity.user.UserRole;
import com.veterinaryserver.exception.ErrorCode;
import com.veterinaryserver.exception.VeterinaryException;
import com.veterinaryserver.model.request.AnimalCreationRequest;
import com.veterinaryserver.model.request.AnimalUpdateRequest;
import com.veterinaryserver.repository.AnimalRepository;

@Service
public class AnimalService {

	
	private final Logger logger = LoggerFactory.getLogger(AnimalService.class);
	
	private UserService userService;
	private AnimalRepository animalRepository;
	private ErrorMessagesService errorMessagesService;

	@Autowired
	public AnimalService(UserService userService, AnimalRepository animalRepository, ErrorMessagesService errorMessagesService) {
		this.userService = userService;
		this.animalRepository = animalRepository;
		this.errorMessagesService = errorMessagesService;
	}
	
	public Animal createAnimal(AnimalCreationRequest request) {
		User user = userService.getLoggedInUser();
		Animal animal = this.parseAnimalCreationRequestToAnimal(request, user);
		Animal saved = this.animalRepository.save(animal);
		return saved;
	}
	
	/**
	 * Finds animal by Id, If couldn't find throws VeterinaryException with NO_RESOURCE clouse
	 * @param id
	 * @return Animal
	 * @throws VeterinaryException NO_RESOURCE
	 */
	public Animal findById(Long id) {
		return this.animalRepository.findById(id).orElseThrow(() -> new VeterinaryException(ErrorCode.NO_RESOURCE, 
				this.errorMessagesService.getMessage(ErrorCode.NO_RESOURCE)));
	}
	
	public List<Animal> findAll(){
		return this.animalRepository.findAll();
	}
	
	public List<Animal> findAnimalWithAnimalNameAndUserName(String q){
		List<Animal> animals = this.animalRepository.findAnimalsByNameOrUserNameOrUserSurname(q);
		return animals;
	}
	
	
	@Transactional
	public void deleteById(Long id) {
		User user = this.userService.getLoggedInUser();
		if(!user.getRole().equals(UserRole.ADMIN)) {
			throw forbiddenException();
		}
		this.animalRepository.deleteById(id);
	}
	
	
	/**
	 * returns new Veterinary Exception with FORBIDDEN clause.
	 * 
	 * 
	 * @return VeterinaryException
	 */
	public VeterinaryException forbiddenException() {
		return new VeterinaryException(ErrorCode.FORBIDDEN, this.errorMessagesService.getMessage(ErrorCode.FORBIDDEN));
	}

	public Boolean isSender(User user, Animal animal) {
		if(animal.getUser().equals(user)) {
			return true;
		}else {
			return false;
		}
	}

	
	public Boolean checkGender(String gender) {
		Gender[] genders = Gender.values();
		for(Gender g : genders) {
			return g.equals(Gender.valueOf(gender)) ? true : false;
		}
		return false;
	}
	
	public Boolean checkType(String type) {
		AnimalType[] types = AnimalType.values();
		for(AnimalType t : types) {
			return t.equals(AnimalType.valueOf(type)) ? true : false;
		}
		return false;
	}

	public List<Animal> findAllByUserId(Long id) {
		List<Animal> animals = this.animalRepository.findAllByUserId(id);
		return animals;
	}

	public List<Animal> searchAnimal(String q) {
		return null;
	}

	public Animal updateAnimal(Long id, AnimalUpdateRequest request) {
		User user = this.userService.getLoggedInUser();
		Animal animal = this.findById(id);
		logger.info("Update - Find, {}", animal.toString());
	    if(!this.isSender(user, animal) && !(user.getRole().equals(UserRole.ADMIN))) {
			logger.info("User and Animal Sender isn't equals, {}, {}, role {}", user.getId(), animal.getUser().getId(), user.getRole());
			throw forbiddenException();
		}
	    Animal updated = parseAnimalUpdateRequest(animal, request);
	    logger.info("Update - Updated, {}", updated.toString());
		Animal saved = this.animalRepository.save(updated);
		logger.info("Update - Saved, {}", saved.toString());
		return saved;
	}
	
	public Animal parseAnimalUpdateRequest(Animal animal, AnimalUpdateRequest request) {
		if(!ObjectUtils.isEmpty(request.getName()) && !request.getName().equals(animal.getName())) {
			animal.setName(request.getName());
		}
		if(!ObjectUtils.isEmpty(request.getAbout()) && !request.getAbout().equals(animal.getAbout())) {
			animal.setAbout(request.getAbout());
		}if(!ObjectUtils.isEmpty(request.getAge()) && request.getAge() != animal.getAge()) {
			animal.setAge(request.getAge());
		}if(!ObjectUtils.isEmpty(request.getBreed()) && !request.getBreed().equals(animal.getBreed())) {
			animal.setBreed(request.getBreed());
		}if(!ObjectUtils.isEmpty(request.getGender()) && !Gender.valueOf(request.getGender()).equals(animal.getGender())) {
			animal.setGender(Gender.valueOf(request.getGender()));
		}if(!ObjectUtils.isEmpty(request.getType()) && !AnimalType.valueOf(request.getType()).equals(animal.getType())) {
			animal.setType(AnimalType.valueOf(request.getType()));
		}
		return animal;
	}
	
	public Animal parseAnimalCreationRequestToAnimal(AnimalCreationRequest request, User user) {
		if(ObjectUtils.isEmpty(request.getName()) || 
			ObjectUtils.isEmpty(request.getAge()) ||
			ObjectUtils.isEmpty(request.getBreed()) ||
			ObjectUtils.isEmpty(request.getGender()) ||
			ObjectUtils.isEmpty(request.getType())) {
			logger.info("Given user creation request has empty fields, {}", request.toString());
			throw new VeterinaryException(ErrorCode.BAD_REQUEST, this.errorMessagesService.getMessage(ErrorCode.BAD_REQUEST));
		}
		return Animal.builder()
				.name(request.getName())
				.about(request.getAbout())
				.age(request.getAge())
				.type(request.getType())
				.gender(request.getGender())
				.breed(request.getBreed())
				.user(user)
				.build();
	}
	
	
}
